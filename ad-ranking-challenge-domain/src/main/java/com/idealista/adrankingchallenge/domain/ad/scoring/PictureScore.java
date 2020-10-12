package com.idealista.adrankingchallenge.domain.ad.scoring;

import com.idealista.adrankingchallenge.domain.ad.Ad;
import com.idealista.adrankingchallenge.domain.ad.Picture;
import com.idealista.adrankingchallenge.domain.ad.ScoreHandler;

public class PictureScore implements ScoreHandler {

  public static final Integer POINTS_WITHOUT_PICTURE = -10;
  public static final Integer POINTS_WITH_HD_PICTURE = 20;
  public static final Integer POINTS_WITH_SD_PICTURE = 10;

  @Override
  public Integer pointsToAdd(Ad ad) {

    int points = 0;

    if (!ad.hasPhoto()) {
      points = points + POINTS_WITHOUT_PICTURE;
    } else {
      int highDefinitionPicturesCount = Math
          .toIntExact(ad.getPictures()
                        .stream()
                        .filter(Picture::isHighDefinition)
                        .count());
      int standardDefinitionPicturesCount = Math
          .toIntExact(ad.getPictures()
                        .stream()
                        .filter(Picture::isStandardDefinition)
                        .count());

      points += (highDefinitionPicturesCount * POINTS_WITH_HD_PICTURE) +
          (standardDefinitionPicturesCount * POINTS_WITH_SD_PICTURE);
    }

    return points;
  }
}
