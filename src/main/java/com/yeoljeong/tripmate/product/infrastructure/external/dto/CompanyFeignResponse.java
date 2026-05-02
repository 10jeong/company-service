package com.yeoljeong.tripmate.product.infrastructure.external.dto;

// Feign 내부 통신 응답 전용 DTO
//
// 공용 ApiResponse 를 그대로 사용하면
// Jackson 역직렬화 과정에서 DecodeException 발생
// (기본 생성자/역직렬화 생성자 문제)
// 따라서 FeignClient 에서만 사용할 전용 응답 DTO를 별도로 생성하여 사용
public record CompanyFeignResponse<T>(
    int code,
    String message,
    T data,
    String timestamp,
    boolean success
) {
}
