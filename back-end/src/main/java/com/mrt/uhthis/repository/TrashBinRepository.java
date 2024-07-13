package com.mrt.uhthis.repository;

import com.mrt.uhthis.entity.TrashBin;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TrashBinRepository extends JpaRepository<TrashBin, Long> {
        List<TrashBin> findByLatitudeBetweenAndLongitudeBetween (Double latMin, Double latMax, Double lonMin, Double lonMax);
}
