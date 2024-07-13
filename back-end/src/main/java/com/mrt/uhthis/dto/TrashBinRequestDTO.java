package com.mrt.uhthis.dto;

import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class TrashBinRequestDTO {

    @NotNull
    private double latitude;

    @NotNull
    private double longitude;

    private double radius; // 1미터 단위
}
