package com.yeoljeong.tripmate.product.infrastructure.external.storage;

import com.yeoljeong.tripmate.exception.BusinessException;
import com.yeoljeong.tripmate.product.application.port.StorageReader;
import com.yeoljeong.tripmate.product.domain.exception.ProductErrorCode;
import io.awspring.cloud.s3.S3Exception;
import io.awspring.cloud.s3.S3Resource;
import io.awspring.cloud.s3.S3Template;
import java.io.IOException;
import java.io.InputStream;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

@Slf4j
@Component
@RequiredArgsConstructor
public class AwsS3ProductStorageClient implements StorageReader {

  private final S3Template s3Template;

  @Value("${spring.cloud.aws.s3.bucket}")
  private String bucketName;

  @Override
  public String upload(MultipartFile file, String fileName) {
    if (file == null || file.isEmpty()) {
      throw new BusinessException(ProductErrorCode.IMAGE_EMPTY_ERROR);
    }
    if (fileName == null || fileName.isBlank()) {
      throw new BusinessException(ProductErrorCode.IMAGE_UPLOAD_ERROR);
    }
    try (InputStream is = file.getInputStream()) {
      S3Resource resource = s3Template.upload(bucketName, fileName, is);
      return resource.getURL().toString();
    } catch (IOException | S3Exception e) {
      log.error("이미지 업로드 중에 에러가 발생하였습니다.", e);
      throw new BusinessException(ProductErrorCode.IMAGE_UPLOAD_ERROR);
    }
  }
}