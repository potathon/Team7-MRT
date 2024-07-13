package com.mrt.uhthis.utils;

import com.mrt.uhthis.dto.TrashBinResponseDTO;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Flux;
import java.util.List;

public interface OpenApiConverter {

    List<TrashBinResponseDTO> getTrashBinsInfo();
}
