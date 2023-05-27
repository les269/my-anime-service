package com.anime.controller;

import com.anime.domain.AnimeBasicInfo;
import com.anime.domain.AnimeDataRequest;
import com.anime.domain.Result;
import com.anime.domain.SearchParam;
import com.anime.service.AnimeInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@CrossOrigin("*")
@RestController
@RequestMapping("api/anime-info")
public class AnimeInfoController {
    @Autowired
    private AnimeInfoService animeInfoService;

    @PostMapping("/update")
    public Result update(@RequestBody AnimeBasicInfo animeBasicInfo){
        return animeInfoService.update(animeBasicInfo);
    }

    @PostMapping("/search")
    public Result search(@RequestBody SearchParam searchParam){
        return animeInfoService.search(searchParam.getName());
    }

    @GetMapping("/list")
    public Result<List<AnimeBasicInfo>> getList(){
        return animeInfoService.getList();
    }

    @PostMapping("/watched")
    public Result<Boolean> watched(@RequestBody AnimeDataRequest animeDataRequest){
        return animeInfoService.setWatched(animeDataRequest.getOfficeName(),animeDataRequest.isWatched());
    }

    @GetMapping("/allWatched")
    public Result<Map<String, Boolean>> getAllWatched(){
        return animeInfoService.getAllWatched();
    }
}
