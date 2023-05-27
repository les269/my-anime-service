package com.anime.service;

import com.anime.configure.Const;
import com.anime.convert.AnimeInfoConvert;
import com.anime.domain.*;
import com.anime.entity.AnimeData;
import com.anime.entity.AnimeInfo;
import com.anime.repository.AnimeDataRepository;
import com.anime.repository.AnimeInfoRepository;
import com.anime.utils.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

import static com.anime.configure.Const.*;

@Service
public class AnimeInfoService {
    @Autowired
    private AnimeInfoRepository animeInfoRepository;

    @Autowired
    private AnimeDataRepository animeDataRepository;

    public Result update(AnimeBasicInfo animeBasicInfo) {
        Result result = new Result(Const.SUCCESS);
        if (StringUtil.isBlank(animeBasicInfo.getOfficialName())) {
            throw new AnimeException("OfficialName is blank");
        }
        AnimeInfo animeInfo = AnimeInfoConvert.INSTANCE.toEntity(animeBasicInfo);
        animeInfoRepository.save(animeInfo);
        return result;
    }

    public Result<AnimeBasicInfo> search(String name) {
        Result<AnimeBasicInfo> result = new Result<>(Const.SUCCESS);
        if (StringUtil.isBlank(name)) {
            throw new AnimeException("name is blank");
        }
        Optional<AnimeInfo> optional = animeInfoRepository.findById(name);
        if (optional.isPresent()) {
            result.setData(AnimeInfoConvert.INSTANCE.toDomain(optional.get()));
            return result;
        }

        optional = animeInfoRepository.findByChineseName(name);
        if (optional.isPresent()) {
            result.setData(AnimeInfoConvert.INSTANCE.toDomain(optional.get()));
            return result;
        }
        return new Result<>(Const.FAIL, "data not find");
    }

    public Result<List<AnimeBasicInfo>> getList() {
        Result<List<AnimeBasicInfo>> result = new Result<>(Const.SUCCESS);
        List<AnimeBasicInfo> animeBasicInfoList = new ArrayList<>();
        animeInfoRepository.findAll().forEach(x -> animeBasicInfoList.add(AnimeInfoConvert.INSTANCE.toDomain(x)));
        result.setData(animeBasicInfoList);
        return result;
    }

    public Result<Boolean> setWatched(String name, boolean watched) {
        Result<Boolean> result = new Result(Const.SUCCESS, "", true);
        if (StringUtil.isBlank(name)) {
            return new Result(Const.FAIL, "", false);
        }
        AnimeData animeData = new AnimeData();
        animeData.setOfficeName(name);
        animeData.setCategory(DATA_CATEGORY_FIXED);
        animeData.setType(DATA_TYPE_WATCHED);
        animeData.setValue(watched ? "Y" : "N");
        animeDataRepository.save(animeData);
        return result;
    }

    public Result<Map<String, Boolean>> getAllWatched() {
        Result<Map<String, Boolean>> result = new Result(SUCCESS, "", new HashMap<>());
        List<AnimeData> animeDataList = animeDataRepository.findByCategoryAndType(DATA_CATEGORY_FIXED, DATA_TYPE_WATCHED);
        if (animeDataList.isEmpty()) {
            result.setData(new HashMap<>());
            return result;
        }
        result.setData(animeDataList.stream().collect(Collectors.toMap(AnimeData::getOfficeName, (animeData -> "Y".equals(animeData.getValue())))));
        return result;
    }
}
