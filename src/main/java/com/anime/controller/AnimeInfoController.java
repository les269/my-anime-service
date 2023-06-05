package com.anime.controller;

import com.anime.domain.*;
import com.anime.entity.AnimeTag;
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
    public Result update(@RequestBody AnimeBasicInfo animeBasicInfo) {
        return animeInfoService.update(animeBasicInfo);
    }

    @PostMapping("/search")
    public Result search(@RequestBody SearchParam searchParam) {
        return animeInfoService.search(searchParam.getName());
    }

    @GetMapping("/list")
    public Result<List<AnimeBasicInfo>> getList() {
        return animeInfoService.getList();
    }

    @PostMapping("/watched")
    public Result<Boolean> watched(@RequestBody AnimeDataRequest animeDataRequest) {
        return animeInfoService.setWatched(animeDataRequest.getOfficialName(), animeDataRequest.isWatched());
    }

    @GetMapping("/allWatched")
    public Result<Map<String, Boolean>> getAllWatched() {
        return animeInfoService.getAllWatched();
    }

    @PostMapping("/deleteAnime")
    public Result<Boolean> deleteAnime(@RequestBody AnimeBasicInfo animeBasicInfo) {
        return animeInfoService.deleteAnime(animeBasicInfo.getOfficialName());
    }

    @PostMapping("/watchProgress")
    public Result<Boolean> watchProgress(@RequestBody AnimeDataRequest animeDataRequest) {
        return animeInfoService.watchProgress(animeDataRequest.getOfficialName(), animeDataRequest.getValue());
    }

    @GetMapping("/allWatchProgress")
    public Result<Map<String, String>> getAllWatchProgress() {
        return animeInfoService.getAllWatchProgress();
    }

    @PostMapping("/message")
    public Result<Boolean> message(@RequestBody AnimeDataRequest animeDataRequest) {
        return animeInfoService.message(animeDataRequest.getOfficialName(), animeDataRequest.getValue());
    }

    @GetMapping("/allMessage")
    public Result<Map<String, String>> getAllMessage() {
        return animeInfoService.getAllMessage();
    }

    @GetMapping("/allTags")
    public Result<List<AnimeTagTO>> getAllTags() {
        return animeInfoService.getAllTags();
    }

    @PostMapping("/updateTag")
    public Result<Boolean> updateTag(@RequestBody AnimeTagTO animeTagTO) {
        return animeInfoService.updateTag(animeTagTO);
    }

    @DeleteMapping("/deleteTag")
    public Result<Boolean> deleteTag(@RequestParam("id") String id) {
        return animeInfoService.deleteTag(id);
    }

    @PostMapping("/updateVideoTag")
    public Result<Boolean> updateVideoTag(@RequestBody VideoTagRequest videoTagRequest){
        return animeInfoService.updateVideoTag(videoTagRequest);
    }

    @GetMapping("/allVideoTags")
    public Result<Map<String,List<String>>> getAllVideoTags() {
        return animeInfoService.getAllVideoTags();
    }
}
