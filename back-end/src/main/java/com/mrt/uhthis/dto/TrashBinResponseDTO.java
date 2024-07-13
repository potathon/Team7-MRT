package com.mrt.uhthis.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class TrashBinResponseDTO {

    private Long id;

    private double latitude;

    private double longitude;

    private String address;

    private String description;

    private String binType;
}
