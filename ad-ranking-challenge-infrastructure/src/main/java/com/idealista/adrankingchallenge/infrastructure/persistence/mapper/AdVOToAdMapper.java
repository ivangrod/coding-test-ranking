package com.idealista.adrankingchallenge.infrastructure.persistence.mapper;

import com.idealista.adrankingchallenge.domain.ad.Ad;
import com.idealista.adrankingchallenge.domain.ad.Typology;
import com.idealista.adrankingchallenge.infrastructure.persistence.AdVO;
import java.util.Collections;
import java.util.function.Function;
import org.springframework.stereotype.Component;

@Component
public class AdVOToAdMapper implements Function<AdVO, Ad> {

  @Override
  public Ad apply(AdVO adVO) {
    return new Ad(adVO.getId(), Typology.valueOf(adVO.getTypology()), adVO.getDescription(),
        Collections.EMPTY_LIST, adVO.getHouseSize(), adVO.getGardenSize());
  }
}
