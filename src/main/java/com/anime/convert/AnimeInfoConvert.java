package com.anime.convert;

import com.anime.domain.AnimeBasicInfo;
import com.anime.entity.AnimeInfo;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.factory.Mappers;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Mapper
public interface AnimeInfoConvert {

    AnimeInfoConvert INSTANCE = Mappers.getMapper(AnimeInfoConvert.class);

    @Mappings({
            @Mapping(target = "author", expression = "java(listToString(domain.getAuthor()))"),
            @Mapping(target = "studio", expression = "java(listToString(domain.getStudio()))"),
            @Mapping(target = "category", expression = "java(listToString(domain.getCategory()))")
    })
    AnimeInfo toEntity(AnimeBasicInfo domain);

    @Mappings({
            @Mapping(target = "author", expression = "java(stringToList(entity.getAuthor()))"),
            @Mapping(target = "studio", expression = "java(stringToList(entity.getStudio()))"),
            @Mapping(target = "category", expression = "java(stringToList(entity.getCategory()))"),
            @Mapping(target = "date", expression = "java(putDate(entity.getStartDate(),entity.getEndDate()))")
    })
    AnimeBasicInfo toDomain(AnimeInfo entity);


    default String listToString(List<String> list) {
        if (list == null) {
            return "";
        }
        return list.stream()
                .collect(Collectors.joining(","));
    }

    default List<String> stringToList(String str) {
        if (str == null || "".equals(str)) {
            return new ArrayList<>();
        }
        return Arrays.asList(str.split(","));
    }

    default List<Date> putDate(Timestamp startDate, Timestamp endDate) {
        if (startDate == null) {
            return new ArrayList<>();
        }
        if (endDate == null) {
            return Arrays.asList(new Date(startDate.getTime()));
        }
        return Arrays.asList(new Date(startDate.getTime()), new Date(endDate.getTime()));
    }
}
