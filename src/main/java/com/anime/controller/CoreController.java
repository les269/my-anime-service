package com.anime.controller;

import com.anime.domain.Result;
import com.anime.service.AnimeInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin("*")
@RestController
@RequestMapping("api/core")
public class CoreController {

    @Autowired
    private AnimeInfoService animeInfoService;

    @GetMapping("/is-alive")
    public Result isAlive() {
        try {
            animeInfoService.search("test");
        } catch (Exception e) {
            return new Result("F", e.getMessage(), false);
        }

        return new Result("S", "", true);
    }
}
