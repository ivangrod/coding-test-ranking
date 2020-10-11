package com.idealista.adrankingchallenge.infrastructure.persistence.mapper;

import com.idealista.adrankingchallenge.domain.ad.Picture;
import com.idealista.adrankingchallenge.domain.ad.Picture.Definition;
import com.idealista.adrankingchallenge.infrastructure.persistence.PictureVO;
import java.util.function.Function;
import org.springframework.stereotype.Component;

@Component
public class PictureVOToPictureMapper implements Function<PictureVO, Picture> {

  @Override
  public Picture apply(PictureVO pictureVO) {
    return new Picture(pictureVO.getId(), pictureVO.getUrl(),
        Definition.valueOf(pictureVO.getQuality()));
  }
}
