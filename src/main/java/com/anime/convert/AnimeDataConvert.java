package com.anime.convert;

import com.anime.domain.AnimeBasicInfo;
import com.anime.domain.AnimeDataResponse;
import com.anime.entity.AnimeData;
import com.anime.entity.AnimeInfo;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.factory.Mappers;

public interface AnimeDataConvert {
    AnimeDataConvert INSTANCE = Mappers.getMapper(AnimeDataConvert.class);

    @Mappings({
    })
    AnimeDataResponse toDomain(AnimeData entity);
}
