package com.mrt.uhthis.utils;

import com.mrt.uhthis.dto.TrashBinResponseDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class KakaoMapApiConverterImpl implements KakaoMapApiConverter {

    private final OpenApiConverter openApiConverter;

    @Override
    public List<TrashBinResponseDTO> convertAddrToPoint() {
        List<TrashBinResponseDTO> trashBinsInfo = openApiConverter.getTrashBinsInfo();

        return List.of();
    }
}
