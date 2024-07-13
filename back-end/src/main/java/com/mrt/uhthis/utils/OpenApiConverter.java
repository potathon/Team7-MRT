package com.mrt.uhthis.utils;

import com.mrt.uhthis.dto.TrashBinResponseDTO;

import java.util.List;

public interface OpenApiConverter {

    List<TrashBinResponseDTO> getTrashBinsInfo();
}
