package com.anime.repository;

import com.anime.entity.AnimeData;
import com.anime.entity.AnimeDataId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface AnimeDataRepository extends JpaRepository<AnimeData, AnimeDataId> {

    @Query(value = "select * from animeData a where a.category = :category and a.type = :type", nativeQuery = true)
    public List<AnimeData> findByCategoryAndType(@Param("category") String category, @Param("type") String type);

    @Query(value = "Delete from AnimeData  where officialName = :officialName and category = :category")
    @Modifying
    @Transactional
    public void deleteByNameAndCategory(@Param("officialName") String officialName, @Param("category") String category);

    @Query(value = "select * from animeData a where a.category = :category", nativeQuery = true)
    public List<AnimeData> findByCategory(@Param("category") String category);
}
