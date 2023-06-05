package com.anime.convert;

import com.anime.domain.AnimeTagTO;
import com.anime.entity.AnimeTag;
import org.mapstruct.Mapper;
import org.mapstruct.Mappings;
import org.mapstruct.factory.Mappers;

import java.util.List;
@Mapper
public interface AnimeTagConvert {
    AnimeTagConvert INSTANCE = Mappers.getMapper(AnimeTagConvert.class);

    @Mappings({
    })
    AnimeTagTO toDomain(AnimeTag entity);
    List<AnimeTagTO> toDomainList(List<AnimeTag> entity);

    @Mappings({
    })
    AnimeTag toEntity(AnimeTagTO domain);
}
