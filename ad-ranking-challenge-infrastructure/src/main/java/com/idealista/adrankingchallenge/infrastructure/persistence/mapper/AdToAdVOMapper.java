package com.idealista.adrankingchallenge.infrastructure.persistence.mapper;

import com.idealista.adrankingchallenge.domain.ad.Ad;
import com.idealista.adrankingchallenge.domain.ad.Picture;
import com.idealista.adrankingchallenge.infrastructure.persistence.AdVO;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

public class AdToAdVOMapper implements Function<Ad, AdVO> {

  @Override
  public AdVO apply(Ad ad) {
    List<Integer> pictureIds = ad.getPictures()
                                 .stream()
                                 .map(Picture::getId)
                                 .collect(Collectors.toList());
    return new AdVO(ad.getId().value(), ad.getTypology().name(), ad.getDescription().value(),
                    pictureIds, ad.getHouseSize().value(), ad.getGardenSize().value(), ad.getScore().value(),
                    ad.getIrrelevantSince().toDate());
  }
}
