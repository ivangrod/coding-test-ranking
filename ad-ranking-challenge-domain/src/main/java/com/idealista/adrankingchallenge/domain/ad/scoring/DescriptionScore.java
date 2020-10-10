package com.idealista.adrankingchallenge.domain.ad.scoring;

import com.idealista.adrankingchallenge.domain.ad.Ad;
import com.idealista.adrankingchallenge.domain.ad.ScoreHandler;
import org.apache.commons.lang3.StringUtils;

public class DescriptionScore implements ScoreHandler {

  private static final Integer POINTS_WITH_DESCRIPTION = 5;

  @Override
  public Integer pointsToAdd(Ad ad) {

    int points = 0;

    if (StringUtils.isNotBlank(ad.getDescription())) {
      points = points + POINTS_WITH_DESCRIPTION;
    }

    return points;
  }
}
