package com.yeoljeong.tripmate.product.application.port;

import org.springframework.web.multipart.MultipartFile;

public interface StorageReader {
  String upload(MultipartFile image, String fileName);
}
