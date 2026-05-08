package com.yeoljeong.tripmate.product.application.service.client;

import com.yeoljeong.tripmate.product.infrastructure.external.dto.CompanyClientResponse;
import java.util.UUID;

public interface CompanyClient {
  CompanyClientResponse getCompany(UUID companyId);
}
