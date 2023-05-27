package com.anime.repository;

import com.anime.entity.AnimeData;
import com.anime.entity.AnimeDataId;
import com.anime.entity.AnimeInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface AnimeDataRepository extends JpaRepository<AnimeData, AnimeDataId> {

    @Query(value = "select * from animeData a where a.category = :category and a.type = :type", nativeQuery = true)
    public List<AnimeData> findByCategoryAndType(@Param("category") String category, @Param("type") String type);
}
