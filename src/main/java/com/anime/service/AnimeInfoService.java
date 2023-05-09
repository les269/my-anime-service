package com.anime.service;

import com.anime.configure.Const;
import com.anime.convert.AnimeInfoConvert;
import com.anime.domain.AnimeBasicInfo;
import com.anime.domain.AnimeException;
import com.anime.domain.Result;
import com.anime.entity.AnimeInfo;
import com.anime.repository.AnimeInfoRepository;
import com.anime.utils.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class AnimeInfoService {
    @Autowired
    private AnimeInfoRepository animeInfoRepository;

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
}
