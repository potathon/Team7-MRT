package com.mrt.uhthis.utils;

import com.mrt.uhthis.dto.TrashBinResponseDTO;
import lombok.extern.slf4j.Slf4j;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@SpringBootTest
class KakaoMapApiConverterImplTest {

    @Autowired
    KakaoMapApiConverter kakaoMapApiConverter;

    @Test
    void convertAddrToPoint() {
        List<TrashBinResponseDTO> list = kakaoMapApiConverter.convertAddrToPoint();

        Assertions.assertThat(list).isNotNull();
        Assertions.assertThat(list).isNotEmpty();

        for (TrashBinResponseDTO trashBinResponseDTO : list) {
            log.info("trashBin info: {}", trashBinResponseDTO);
        }
    }
}