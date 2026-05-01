package com.yeoljeong.tripmate.product.application.service.client;

import com.yeoljeong.tripmate.company.presentation.dto.response.CompanyResponse;
import java.util.UUID;

public interface CompanyClient {

  CompanyResponse getCompany(UUID companyId);
}
