package com.mrt.uhthis.entity;

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
}