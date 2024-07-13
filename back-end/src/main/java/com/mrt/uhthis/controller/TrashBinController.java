package com.mrt.uhthis.controller;

import com.mrt.uhthis.dto.TrashBinRequestDTO;
import com.mrt.uhthis.dto.TrashBinResponseDTO;
import com.mrt.uhthis.entity.TrashBin;
import com.mrt.uhthis.service.DatabaseRefreshService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class TrashBinController {

    private final DatabaseRefreshService databaseRefreshService;

    @PostMapping("/trashbins")
    public ResponseEntity<?> getNearbyTrashBins (@Valid @RequestBody TrashBinRequestDTO requestDTO, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            log.error("파라미터에 문제가 있습니다");
            return ResponseEntity.badRequest().build();
        }

        List<TrashBinResponseDTO> trashBins = databaseRefreshService.getNearbyTrashBins(requestDTO);

        return ResponseEntity.ok().body(trashBins);
    }

}
