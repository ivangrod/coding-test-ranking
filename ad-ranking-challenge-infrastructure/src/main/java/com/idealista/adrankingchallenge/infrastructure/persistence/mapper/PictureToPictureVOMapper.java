package com.idealista.adrankingchallenge.infrastructure.persistence.mapper;

import com.idealista.adrankingchallenge.domain.ad.Picture;
import com.idealista.adrankingchallenge.infrastructure.persistence.PictureVO;
import java.util.function.Function;
import org.springframework.stereotype.Component;

@Component
public class PictureToPictureVOMapper implements Function<Picture, PictureVO> {

  @Override
  public PictureVO apply(Picture picture) {
    return new PictureVO(picture.getId(), picture.getUrl(), picture.getDefinition()
                                                                   .name());
  }
}
