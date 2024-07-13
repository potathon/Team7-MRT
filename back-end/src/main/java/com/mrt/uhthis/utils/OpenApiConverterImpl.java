package com.mrt.uhthis.utils;

import com.mrt.uhthis.dto.TrashBinResponseDTO;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class OpenApiConverterImpl implements OpenApiConverter {

    @Override
    public List<TrashBinResponseDTO> getTrashBinsInfo() {
        return List.of();
    }
}
