package com.mrt.uhthis.utils;

import com.mrt.uhthis.dto.TrashBinResponseDTO;

import java.util.List;

public interface KakaoMapApiConverter {

    List<TrashBinResponseDTO> convertAddrToPoint();

    List<TrashBinResponseDTO> convertAddrToPoint(List<TrashBinResponseDTO> list);
}
