package com.anime.convert;

import com.anime.domain.AnimeBasicInfo;
import com.anime.entity.AnimeInfo;
import javax.annotation.processing.Generated;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2023-05-25T20:38:11+0800",
    comments = "version: 1.5.3.Final, compiler: javac, environment: Java 11.0.17 (Oracle Corporation)"
)
public class AnimeInfoConvertImpl implements AnimeInfoConvert {

    @Override
    public AnimeInfo toEntity(AnimeBasicInfo domain) {
        if ( domain == null ) {
            return null;
        }

        AnimeInfo animeInfo = new AnimeInfo();

        animeInfo.setOfficialName( domain.getOfficialName() );
        animeInfo.setChineseName( domain.getChineseName() );
        animeInfo.setStartDate( domain.getStartDate() );
        animeInfo.setEndDate( domain.getEndDate() );
        animeInfo.setEpisode( domain.getEpisode() );
        animeInfo.setWikiUrl( domain.getWikiUrl() );
        animeInfo.setImgUrl( domain.getImgUrl() );
        animeInfo.setOutline( domain.getOutline() );

        animeInfo.setAuthor( listToString(domain.getAuthor()) );
        animeInfo.setStudio( listToString(domain.getStudio()) );
        animeInfo.setCategory( listToString(domain.getCategory()) );

        return animeInfo;
    }

    @Override
    public AnimeBasicInfo toDomain(AnimeInfo entity) {
        if ( entity == null ) {
            return null;
        }

        AnimeBasicInfo animeBasicInfo = new AnimeBasicInfo();

        animeBasicInfo.setOfficialName( entity.getOfficialName() );
        animeBasicInfo.setChineseName( entity.getChineseName() );
        animeBasicInfo.setStartDate( entity.getStartDate() );
        animeBasicInfo.setEndDate( entity.getEndDate() );
        animeBasicInfo.setEpisode( entity.getEpisode() );
        animeBasicInfo.setWikiUrl( entity.getWikiUrl() );
        animeBasicInfo.setImgUrl( entity.getImgUrl() );
        animeBasicInfo.setOutline( entity.getOutline() );

        animeBasicInfo.setAuthor( stringToList(entity.getAuthor()) );
        animeBasicInfo.setStudio( stringToList(entity.getStudio()) );
        animeBasicInfo.setCategory( stringToList(entity.getCategory()) );
        animeBasicInfo.setDate( putDate(entity.getStartDate(),entity.getEndDate()) );

        return animeBasicInfo;
    }
}
