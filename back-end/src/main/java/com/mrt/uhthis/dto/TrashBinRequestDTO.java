package com.mrt.uhthis.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class TrashBinRequestDTO {

    private double latitude;

    private double longitude;

    private Double radius;
}
