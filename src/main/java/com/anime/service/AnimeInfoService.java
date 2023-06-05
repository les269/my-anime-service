package com.anime.service;

import com.anime.configure.Const;
import com.anime.convert.AnimeInfoConvert;
import com.anime.convert.AnimeTagConvert;
import com.anime.domain.*;
import com.anime.entity.AnimeData;
import com.anime.entity.AnimeInfo;
import com.anime.entity.AnimeTag;
import com.anime.repository.AnimeDataRepository;
import com.anime.repository.AnimeInfoRepository;
import com.anime.repository.AnimeTagRepository;
import com.anime.utils.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import java.util.*;
import java.util.stream.Collectors;

import static com.anime.configure.Const.*;

@Service
public class AnimeInfoService {
    @Autowired
    private AnimeInfoRepository animeInfoRepository;

    @Autowired
    private AnimeDataRepository animeDataRepository;

    @Autowired
    private AnimeTagRepository animeTagRepository;

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
        animeData.setOfficialName(name);
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
        result.setData(animeDataList.stream().collect(Collectors.toMap(AnimeData::getOfficialName, (animeData -> "Y".equals(animeData.getValue())))));
        return result;
    }

    public Result<Boolean> deleteAnime(String officialName) {
        animeInfoRepository.deleteById(officialName);
        return new Result<>(SUCCESS, "", true);
    }

    public Result<Boolean> watchProgress(String name, String value) {
        Result<Boolean> result = new Result(Const.SUCCESS, "", true);
        if (StringUtil.isBlank(name)) {
            return new Result(Const.FAIL, "", false);
        }
        AnimeData animeData = new AnimeData();
        animeData.setOfficialName(name);
        animeData.setCategory(DATA_CATEGORY_FIXED);
        animeData.setType(DATA_TYPE_WATCH_PROGRESS);
        animeData.setValue(value);
        animeDataRepository.save(animeData);
        return result;
    }

    public Result<Map<String, String>> getAllWatchProgress() {
        Result<Map<String, String>> result = new Result(SUCCESS, "", new HashMap<>());
        List<AnimeData> animeDataList = animeDataRepository.findByCategoryAndType(DATA_CATEGORY_FIXED, DATA_TYPE_WATCH_PROGRESS);
        if (animeDataList.isEmpty()) {
            result.setData(new HashMap<>());
            return result;
        }
        result.setData(animeDataList.stream().collect(Collectors.toMap(AnimeData::getOfficialName, AnimeData::getValue)));
        return result;
    }

    public Result<Boolean> message(String name, String value) {
        Result<Boolean> result = new Result(Const.SUCCESS, "", true);
        if (StringUtil.isBlank(name)) {
            return new Result(Const.FAIL, "", false);
        }
        AnimeData animeData = new AnimeData();
        animeData.setOfficialName(name);
        animeData.setCategory(DATA_CATEGORY_FIXED);
        animeData.setType(DATA_TYPE_MESSAGE);
        animeData.setValue(value);
        animeDataRepository.save(animeData);
        return result;
    }

    public Result<Map<String, String>> getAllMessage() {
        Result<Map<String, String>> result = new Result(SUCCESS, "", new HashMap<>());
        List<AnimeData> animeDataList = animeDataRepository.findByCategoryAndType(DATA_CATEGORY_FIXED, DATA_TYPE_MESSAGE);
        if (animeDataList.isEmpty()) {
            result.setData(new HashMap<>());
            return result;
        }
        result.setData(animeDataList.stream().collect(Collectors.toMap(AnimeData::getOfficialName, AnimeData::getValue)));
        return result;
    }

    public Result<List<AnimeTagTO>> getAllTags() {
        return new Result<>(SUCCESS, "", AnimeTagConvert.INSTANCE.toDomainList(animeTagRepository.findAll()));
    }

    public Result<Boolean> deleteTag(String id) {
        animeTagRepository.deleteById(id);
        return new Result<>(SUCCESS, "", true);
    }

    public Result<Boolean> updateTag(AnimeTagTO animeTagTO) {
        animeTagRepository.save(AnimeTagConvert.INSTANCE.toEntity(animeTagTO));
        return new Result<>(SUCCESS, "", true);
    }

    @Transactional
    public Result<Boolean> updateVideoTag(VideoTagRequest videoTagRequest) {
        Result<Boolean> result = new Result<>(SUCCESS, "", true);
        animeDataRepository.deleteByNameAndCategory(videoTagRequest.getOfficialName(), DATA_CATEGORY_TAG);
        if (!CollectionUtils.isEmpty(videoTagRequest.getIdList())) {
            List<AnimeData> entities = videoTagRequest.getIdList().stream().map(id -> {
                AnimeData entity = new AnimeData();
                entity.setOfficialName(videoTagRequest.getOfficialName());
                entity.setCategory(DATA_CATEGORY_TAG);
                entity.setType(id);
                entity.setValue("Y");
                return entity;
            }).collect(Collectors.toList());
            animeDataRepository.saveAll(entities);
        }

        return result;
    }

    public Result<Map<String, List<String>>> getAllVideoTags() {
        Result<Map<String, List<String>>> result = new Result(SUCCESS, "", new HashMap<>());
        List<AnimeData> animeDataList = animeDataRepository.findByCategory(DATA_CATEGORY_TAG);
        if (animeDataList.isEmpty()) {
            result.setData(new HashMap<>());
            return result;
        }
        result.setData(animeDataList.stream().collect(
                Collectors.groupingBy(AnimeData::getOfficialName,
                        Collectors.mapping(AnimeData::getType, Collectors.toList()))));
        return result;
    }
}
