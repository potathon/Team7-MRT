package com.mrt.uhthis.utils;

import com.mrt.uhthis.dto.OpenApiResponse;
import com.mrt.uhthis.dto.OpenApiResponse.TrashBinData;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.http.client.reactive.ClientHttpConnector;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;

@Slf4j
@Component
public class OpenApiConverterImpl implements OpenApiConverter {

    private final WebClient webClient;
    private final String apiUrl;
    private final String apiKey;

    public OpenApiConverterImpl(WebClient.Builder webClientBuilder, ClientHttpConnector clientHttpConnector,
                                @Value("${openapi.url}") String apiUrl,
                                @Value("${openapi.key}") String apiKey) {
        this.webClient = webClientBuilder
                .baseUrl(apiUrl)
                .clientConnector(clientHttpConnector)
                .build();
        this.apiUrl = apiUrl;
        this.apiKey = apiKey;
    }

    @Override
    public List<TrashBinData> getTrashBinsInfo() {
        OpenApiResponse initialResponse;
        try {
            // 첫 번째 요청
            initialResponse = this.webClient.get()
                    .uri(new URI(apiUrl + "?serviceKey=" + apiKey + "&page=1&perPage=10"))
                    .retrieve()
                    .bodyToMono(OpenApiResponse.class)
                    .block();
        } catch (URISyntaxException e) {
            throw new RuntimeException(e);
        }

        int totalCount = initialResponse.getTotalCount();
        OpenApiResponse finalResponse;
        try {
            // 두 번째 요청
            finalResponse = this.webClient.get()
                    .uri(new URI(apiUrl + "?serviceKey=" + apiKey + "&page=1&perPage=" + totalCount))
                    .retrieve()
                    .bodyToMono(OpenApiResponse.class)
                    .block();
        } catch (URISyntaxException e) {
            throw new RuntimeException(e);
        }

        return finalResponse.getData();
    }
}