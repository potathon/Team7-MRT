package com.mrt.uhthis.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class OpenApiResponse {
    private List<TrashBinData> data;

    private int totalCount;
    @Getter
    @ToString
    @NoArgsConstructor
    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class TrashBinData {
        @JsonProperty("설치위치")
        private String address;

        @JsonProperty("세부위치")
        private String description;

        @JsonProperty("종류")
        private String binType;
    }
}
