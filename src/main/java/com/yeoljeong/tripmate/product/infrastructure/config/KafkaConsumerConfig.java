package com.yeoljeong.tripmate.product.infrastructure.config;

import lombok.RequiredArgsConstructor;
import org.apache.kafka.common.TopicPartition;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.core.ConsumerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.listener.ContainerProperties;
import org.springframework.kafka.listener.DeadLetterPublishingRecoverer;
import org.springframework.kafka.listener.DefaultErrorHandler;
import org.springframework.util.backoff.FixedBackOff;

//에러 처리 및 재시도 로직이 포함된 Kafka Consumer 설정 추가
@Configuration
@RequiredArgsConstructor
public class KafkaConsumerConfig {

  // yaml에서 재시도 설정값 주입
  @Value("${spring.retry.interval}")
  private long retryInterval;

  @Value("${spring.retry.max-attempts}")
  private long maxAttempts;


  /**
   * 공통 Kafka 리스너 컨테이너 팩토리
   *
   * - ConsumerFactory : Kafka Consumer 연결/역직렬화 설정
   * - AckMode.MANUAL  : acknowledge() 호출 시에만 offset 커밋
   * - FixedBackOff    : 실패 시 일정 간격으로 재시도
   * - DeadLetterPublishingRecoverer : 재시도 초과 시 .DLT 토픽으로 전송
   *
   * Object 제네릭 사용 이유:
   * Java 타입 소거로 인해 <T>로 빈 등록 불가.
   * 대신 메시지 헤더의 __TypeId__ 정보로 각 이벤트 클래스를 자동 역직렬화.
   */

  @Bean
  public ConcurrentKafkaListenerContainerFactory<String, Object> kafkaListenerContainerFactory(
      ConsumerFactory<String, Object> consumerFactory,
      KafkaTemplate<String, Object> kafkaTemplate
  ) {
    var factory = new ConcurrentKafkaListenerContainerFactory<String, Object>();

    // Kafka 연결 및 역직렬화 설정 적용
    factory.setConsumerFactory(consumerFactory);

    // 수동 커밋 모드: acknowledge() 호출 전까지 offset 커밋 안 함
    factory.getContainerProperties().setAckMode(ContainerProperties.AckMode.MANUAL);

    // 재시도 초과 시 원래토픽명.DLT 로 메시지 이동
    var recoverer = new DeadLetterPublishingRecoverer(kafkaTemplate,
        (record, ex) -> new TopicPartition(record.topic() + ".DLT", record.partition()));

    // retryInterval(ms) 간격으로 maxAttempts회 재시도 후 recoverer 실행
    var errorHandler = new DefaultErrorHandler(recoverer, new FixedBackOff(retryInterval, maxAttempts));
    factory.setCommonErrorHandler(errorHandler);

    return factory;
  }
}