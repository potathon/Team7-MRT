package com.mrt.uhthis.service;

import com.mrt.uhthis.dto.TrashBinResponseDTO;
import com.mrt.uhthis.repository.TrashBinRepository;
import com.mrt.uhthis.utils.KakaoMapApiConverter;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class DatabaseRefreshServiceImpl implements DatabaseRefreshService {

    private final TrashBinRepository trashBinRepository;
    private final KakaoMapApiConverter kakaoMapApiConverter;

    @Override
    @Transactional
    public void refreshDatabase() {
        List<TrashBinResponseDTO> trashBinsInfo = kakaoMapApiConverter.convertAddrToPoint();

    }
}
