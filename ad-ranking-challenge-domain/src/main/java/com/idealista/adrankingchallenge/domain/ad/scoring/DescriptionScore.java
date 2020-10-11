package com.idealista.adrankingchallenge.domain.ad.scoring;

import com.idealista.adrankingchallenge.domain.ad.Ad;
import com.idealista.adrankingchallenge.domain.ad.ScoreHandler;
import org.apache.commons.lang3.StringUtils;

public class DescriptionScore implements ScoreHandler {

  private static final Integer POINTS_WITH_DESCRIPTION = 5;
  private static final Integer POINTS_FLAT_WITH_MEDIUM_DESCRIPTION = 10;
  private static final Integer POINTS_FLAT_WITH_LONG_DESCRIPTION = 30;
  private static final Integer POINTS_CHALET_WITH_LONG_DESCRIPTION = 20;

  @Override
  public Integer pointsToAdd(Ad ad) {

    int points = 0;
    if (StringUtils.isBlank(ad.getDescription())) {
      return points;
    }

    points = points + POINTS_WITH_DESCRIPTION;

    if (ad.isAFlatWithMediumDescription()) {
      points = points + POINTS_FLAT_WITH_MEDIUM_DESCRIPTION;
    }

    if (ad.isAFlatWithLongDescription()) {
      points = points + POINTS_FLAT_WITH_LONG_DESCRIPTION;
    }

    if (ad.isAChaletWithLongDescription()) {
      points = points + POINTS_CHALET_WITH_LONG_DESCRIPTION;
    }

    return points;
  }
}
