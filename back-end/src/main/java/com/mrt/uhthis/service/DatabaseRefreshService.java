package com.mrt.uhthis.service;

import com.mrt.uhthis.entity.TrashBin;

import java.util.List;

public interface DatabaseRefreshService {
    List<TrashBin> getNearbyTrashBins(Double lat, Double lon, Double radius);
    void refreshDatabase();
}
