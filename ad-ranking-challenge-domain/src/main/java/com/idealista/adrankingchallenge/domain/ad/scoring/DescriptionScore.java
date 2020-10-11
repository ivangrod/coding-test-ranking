package com.idealista.adrankingchallenge.domain.ad.scoring;

import com.idealista.adrankingchallenge.domain.ad.Ad;
import com.idealista.adrankingchallenge.domain.ad.ScoreHandler;

public class DescriptionScore implements ScoreHandler {

  private static final Integer POINTS_WITH_DESCRIPTION = 5;
  private static final Integer POINTS_FLAT_WITH_MEDIUM_DESCRIPTION = 10;
  private static final Integer POINTS_FLAT_WITH_LONG_DESCRIPTION = 30;
  private static final Integer POINTS_CHALET_WITH_LONG_DESCRIPTION = 20;
  private static final Integer POINTS_WITH_KEYWORD_DESCRIPTION = 5;

  @Override
  public Integer pointsToAdd(Ad ad) {

    int points = 0;
    if (!ad.hasDescription()) {
      return points;
    }

    points += POINTS_WITH_DESCRIPTION;

    points +=
        ad.numberOfOccurrencesWithKeywordsInTheDescription() * POINTS_WITH_KEYWORD_DESCRIPTION;

    if (ad.isAFlatWithMediumDescription()) {
      points += POINTS_FLAT_WITH_MEDIUM_DESCRIPTION;
    }

    if (ad.isAFlatWithLongDescription()) {
      points += POINTS_FLAT_WITH_LONG_DESCRIPTION;
    }

    if (ad.isAChaletWithLongDescription()) {
      points += POINTS_CHALET_WITH_LONG_DESCRIPTION;
    }

    return points;
  }
}
