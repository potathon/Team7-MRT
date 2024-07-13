package com.mrt.uhthis.utils;

import com.mrt.uhthis.dto.OpenApiResponse;
import com.mrt.uhthis.dto.OpenApiResponse.TrashBinData;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.client.reactive.ClientHttpConnector;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.List;

@SpringBootTest
public class OpenApiConverterImplTest {

    @Autowired
    private WebClient.Builder webClientBuilder;

    @Autowired
    private ClientHttpConnector clientHttpConnector;

    @Value("${openapi.url}")
    private String apiUrl;

    @Value("${openapi.key}")
    private String apiKey;

    @Test
    public void testGetTrashBinsInfo() {
        OpenApiConverter openApiConverter = new OpenApiConverterImpl(webClientBuilder, clientHttpConnector, apiUrl, apiKey);
        List<TrashBinData> trashBins = openApiConverter.getTrashBinsInfo();

        // 결과 출력
        trashBins.forEach(trashBin -> {
            System.out.println("Address: " + trashBin.getAddress());
            System.out.println("Description: " + trashBin.getDescription());
            System.out.println("Bin Type: " + trashBin.getBinType());
            System.out.println("---------------------------------");
        });
    }
}
