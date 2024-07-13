package com.mrt.uhthis.utils;

import com.mrt.uhthis.dto.OpenApiResponse.TrashBinData;
import java.util.List;

public interface OpenApiConverter {
    List<TrashBinData> getTrashBinsInfo();
}
