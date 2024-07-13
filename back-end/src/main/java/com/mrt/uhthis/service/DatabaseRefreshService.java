package com.mrt.uhthis.service;

import com.mrt.uhthis.dto.TrashBinRequestDTO;
import com.mrt.uhthis.dto.TrashBinResponseDTO;
import com.mrt.uhthis.entity.TrashBin;

import java.util.List;

public interface DatabaseRefreshService {
    List<TrashBinResponseDTO> getNearbyTrashBins(TrashBinRequestDTO requestDTO);
    void refreshDatabase();
}
