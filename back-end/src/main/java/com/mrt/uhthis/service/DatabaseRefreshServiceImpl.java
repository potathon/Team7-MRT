package com.mrt.uhthis.service;

import com.mrt.uhthis.dto.TrashBinRequestDTO;
import com.mrt.uhthis.dto.TrashBinResponseDTO;
import com.mrt.uhthis.entity.TrashBin;
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

    public List<TrashBinResponseDTO> getNearbyTrashBins (TrashBinRequestDTO requestDTO) {
        Double lat = requestDTO.getLatitude();
        Double lon = requestDTO.getLongitude();
        Double radius = requestDTO.getRadius();

        Double latMin = lat - radius;
        Double latMax = lat + radius;
        Double lonMin = lon - radius;
        Double lonMax = lon + radius;

        List<TrashBin> trashBins = trashBinRepository.findByLatitudeBetweenAndLongitudeBetween(latMin, latMax, lonMin, lonMax);

        return trashBins.stream()
                .map(TrashBin::toResponseDTO)
                .toList();
    }
}
