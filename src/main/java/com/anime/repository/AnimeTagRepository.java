package com.anime.repository;

import com.anime.entity.AnimeTag;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AnimeTagRepository extends JpaRepository<AnimeTag,String> {

}
