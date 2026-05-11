package com.yeoljeong.tripmate.product.infrastructure.external.dto;

import java.util.UUID;

public record CompanyClientResponse(
    UUID createdBy,
    boolean active
) {}
