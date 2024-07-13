package com.mrt.uhthis.entity;

import com.mrt.uhthis.dto.TrashBinResponseDTO;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "trash_bin")
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TrashBin {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private double latitude;

    private double longitude;

    private String address;

    private String description;

    @Column(name = "bin_type")
    private String binType;

    // toResponseDto: 엔티티를 responseDto로 만들겠다
    public TrashBinResponseDTO toResponseDTO() {
        return TrashBinResponseDTO.builder()
                .id(this.id)
                .latitude(this.latitude)
                .longitude(this.longitude)
                .address(this.address)
                .description(this.description)
                .binType(this.binType)
                .build();
    }
}