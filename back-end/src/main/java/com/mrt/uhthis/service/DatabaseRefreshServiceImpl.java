package com.mrt.uhthis.service;

import com.mrt.uhthis.dto.TrashBinRequestDTO;
import com.mrt.uhthis.dto.TrashBinResponseDTO;
import com.mrt.uhthis.entity.TrashBin;
import com.mrt.uhthis.repository.TrashBinRepository;
import com.mrt.uhthis.utils.KakaoMapApiConverter;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class DatabaseRefreshServiceImpl implements DatabaseRefreshService {

    private final TrashBinRepository trashBinRepository;
    private final KakaoMapApiConverter kakaoMapApiConverter;

    private static final double LATITUDE_VALUE = 1 / 109.958489129649955;
    private static final double LONGITUDE_VALUE = 1 / 88.74;

    @Override
    @Transactional
    public void refreshDatabase() {
        List<TrashBinResponseDTO> trashBinsInfo = kakaoMapApiConverter.convertAddrToPoint();

        for (TrashBinResponseDTO trashBinResponseDTO : trashBinsInfo) {
            trashBinRepository.save(TrashBin.builder()
                    .address(trashBinResponseDTO.getAddress())
                    .binType(trashBinResponseDTO.getBinType())
                    .description(trashBinResponseDTO.getDescription())
                    .longitude(trashBinResponseDTO.getLongitude())
                    .latitude(trashBinResponseDTO.getLatitude())
                    .build());
        }
    }

    @Transactional
    public List<TrashBinResponseDTO> getNearbyTrashBins (TrashBinRequestDTO requestDTO) {
        double lat = requestDTO.getLatitude();
        double lon = requestDTO.getLongitude();
        double radius = requestDTO.getRadius();

        double latMin = lat - (LATITUDE_VALUE / 1000 * radius); // radius=1 이면 1미터 -> 사용자를 중심으로 상하좌우 1미터씩
        double latMax = lat + (LATITUDE_VALUE / 1000 * radius);
        double lonMin = lon - (LONGITUDE_VALUE / 1000 * radius);
        double lonMax = lon + (LONGITUDE_VALUE / 1000 * radius);

        log.info("latMin: {}, latMax: {}", latMin, latMax);
        log.info("lonMin: {}, lonMax: {}", lonMin, lonMax);

        List<TrashBin> trashBins = trashBinRepository.findByLatitudeBetweenAndLongitudeBetween(latMin, latMax, lonMin, lonMax);

        return trashBins.stream()
                .map(TrashBin::toResponseDTO)
                .toList();
    }
}
