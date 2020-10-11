package com.idealista.adrankingchallenge.infrastructure.persistence.mapper;

import com.idealista.adrankingchallenge.domain.ad.Ad;
import com.idealista.adrankingchallenge.domain.ad.Typology;
import com.idealista.adrankingchallenge.infrastructure.persistence.AdVO;
import com.idealista.adrankingchallenge.infrastructure.persistence.PictureVO;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.stereotype.Component;

@Component
public class AdVOToAdMapper {

  private final PictureVOToPictureMapper pictureVOToPictureMapper;

  public AdVOToAdMapper(PictureVOToPictureMapper pictureVOToPictureMapper) {
    this.pictureVOToPictureMapper = pictureVOToPictureMapper;
  }

  public Ad convert(AdVO adVO, List<PictureVO> picturesVORelated) {
    // TODO Manager Date
    return new Ad(adVO.getId(), Typology.valueOf(adVO.getTypology()), adVO.getDescription(),
                  picturesVORelated.stream()
                                   .map(pictureVOToPictureMapper)
                                   .collect(Collectors.toList()),
                  adVO.getHouseSize(), adVO.getGardenSize(), adVO.getScore(),
                  null);
  }
}
