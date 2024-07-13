package com.mrt.uhthis.utils;

import com.mrt.uhthis.dto.TrashBinResponseDTO;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.List;

@SpringBootTest
public class OpenApiConverterImplTest {

    @Autowired
    private WebClient.Builder webClientBuilder;

    @Test
    public void testGetTrashBinsInfo() {
        OpenApiConverter openApiConverter = new OpenApiConverterImpl(webClientBuilder);
        List<TrashBinResponseDTO> trashBins = openApiConverter.getTrashBinsInfo();

        // 결과 출력
        trashBins.forEach(trashBin -> {
            System.out.println("Address: " + trashBin.getAddress());
            System.out.println("Latitude: " + trashBin.getLatitude());
            System.out.println("Longitude: " + trashBin.getLongitude());
            System.out.println("---------------------------------");
        });
    }
}
