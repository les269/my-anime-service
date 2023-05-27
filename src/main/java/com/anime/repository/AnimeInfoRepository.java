package com.anime.repository;

import com.anime.entity.AnimeInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface AnimeInfoRepository extends JpaRepository<AnimeInfo, String> {

    @Query(value = "select * from AnimeInfo a where a.chineseName=:chineseName",nativeQuery=true)
    public Optional<AnimeInfo> findByChineseName(@Param("chineseName")String chineseName);
}
