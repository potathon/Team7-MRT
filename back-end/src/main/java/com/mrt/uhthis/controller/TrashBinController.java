package com.mrt.uhthis.controller;

import com.mrt.uhthis.dto.TrashBinRequestDTO;
import com.mrt.uhthis.dto.TrashBinResponseDTO;
import com.mrt.uhthis.service.DatabaseRefreshService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class TrashBinController {

    private final DatabaseRefreshService databaseRefreshService;

    @PostMapping("/trashbins")
    public ResponseEntity<?> getNearbyTrashBins(@Valid @RequestBody TrashBinRequestDTO requestDTO, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            log.error("파라미터에 문제가 있습니다: {}", bindingResult.getAllErrors());
            return ResponseEntity.badRequest().build();
        }

        try {
            List<TrashBinResponseDTO> trashBins = databaseRefreshService.getNearbyTrashBins(requestDTO);
            return ResponseEntity.ok().body(trashBins);
        } catch (Exception e) {
            log.error("서버에서 에러가 발생했습니다: ", e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

}
