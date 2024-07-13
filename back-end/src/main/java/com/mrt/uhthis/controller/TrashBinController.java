package com.mrt.uhthis.controller;

import com.mrt.uhthis.dto.TrashBinRequestDTO;
import com.mrt.uhthis.dto.TrashBinResponseDTO;
import com.mrt.uhthis.entity.TrashBin;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class TrashBinController {

    @PostMapping("/trashbins")
    public List<TrashBinResponseDTO> getNearbyTrashBins (@RequestBody TrashBinRequestDTO requestDTO) {
        List<TrashBin> trashBins = databaseRefreshService.getNearbyTrashBins(requestDTO.getLatitude(), requestDTO.getLongitude(), requestDTO.getRadius());
        return trashBins.stream()
                .map(trashBin -> new TrashBinResponseDTO(
                        trashBin.getId(),
                        trashBin.getLatitude(),
                        trashBin.getLongitude(),
                        trashBin.getAddress(),
                        trashBin.getDescription(),
                        trashBin.getBinType()
                )).collect(Collectors.toList());
    }

}
