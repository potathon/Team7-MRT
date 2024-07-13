package com.mrt.uhthis.utils;

import com.mrt.uhthis.dto.TrashBinResponseDTO;
import org.springframework.stereotype.Component;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;

import java.util.List;

@Component
public class OpenApiConverterImpl implements OpenApiConverter {

    private final WebClient webClient;
    private final String apiUrl = "https://infuser.odcloud.kr/oas/docs?namespace=15096706/v1";
    private final String apiKey = "S08Fw9PW%2FIG0liH%2BLDMp0U7zUV2iBhAy15%2BuPycbKuy23sip0h%2BBBb22CwjefKhoLIpfG7hbRRFYn3Zr03RsWA%3D%3D";


    public OpenApiConverterImpl(WebClient.Builder webClientBuilder) {
        this.webClient = webClientBuilder.baseUrl(apiUrl).build();
    }
//    public OpenApiConverterImpl(WebClient.Builder webClientBuilder,
//                                @Value("${openapi.url}") String apiUrl,
//                                @Value("${openapi.key}") String apiKey) {
//        this.webClient = webClientBuilder.baseUrl(apiUrl).build();
////        this.apiUrl = apiUrl; TODO: applicaiton.yml에 apiurl, apikey추가되면 주석해제
////        this.apiKey = apiKey;
//    }

    @Override
    public List<TrashBinResponseDTO> getTrashBinsInfo() {
        Flux<TrashBinResponseDTO> responseFlux = this.webClient.get()
                .uri(uriBuilder -> uriBuilder
                        .queryParam("serviceKey", apiKey)
                        .queryParam("page", 2)
                        .queryParam("perPage", 10)
                        .build())
                .retrieve()
                .bodyToFlux(TrashBinResponseDTO.class);

        return responseFlux.collectList().block();
    }
}
