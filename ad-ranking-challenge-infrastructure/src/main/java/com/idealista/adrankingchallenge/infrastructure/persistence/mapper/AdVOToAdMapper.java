package com.idealista.adrankingchallenge.infrastructure.persistence.mapper;

import com.idealista.adrankingchallenge.domain.ad.Ad;
import com.idealista.adrankingchallenge.domain.ad.AdDate;
import com.idealista.adrankingchallenge.domain.ad.AdDescription;
import com.idealista.adrankingchallenge.domain.ad.AdId;
import com.idealista.adrankingchallenge.domain.ad.AdScore;
import com.idealista.adrankingchallenge.domain.ad.AdSize;
import com.idealista.adrankingchallenge.domain.ad.Picture;
import com.idealista.adrankingchallenge.domain.ad.Typology;
import com.idealista.adrankingchallenge.infrastructure.persistence.AdVO;
import com.idealista.adrankingchallenge.infrastructure.persistence.PictureVO;
import java.util.List;
import java.util.stream.Collectors;

public class AdVOToAdMapper {

  private final PictureVOToPictureMapper pictureVOToPictureMapper;

  public AdVOToAdMapper(PictureVOToPictureMapper pictureVOToPictureMapper) {
    this.pictureVOToPictureMapper = pictureVOToPictureMapper;
  }

  public Ad convert(AdVO adVO, List<PictureVO> picturesVORelated) {
    List<Picture> pictures = picturesVORelated.stream()
                                              .map(pictureVOToPictureMapper)
                                              .collect(Collectors.toList());
    return Ad
        .of(new AdId(adVO.getId()), Typology.valueOf(adVO.getTypology()), new AdDescription(adVO.getDescription()),
            pictures, new AdSize(adVO.getHouseSize()), new AdSize(adVO.getGardenSize()),
            new AdScore(adVO.getScore()), new AdDate(adVO.getIrrelevantSince()));
  }
}
