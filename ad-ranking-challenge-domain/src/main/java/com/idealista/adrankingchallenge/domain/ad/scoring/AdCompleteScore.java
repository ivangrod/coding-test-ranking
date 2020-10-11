package com.idealista.adrankingchallenge.domain.ad.scoring;

import com.idealista.adrankingchallenge.domain.ad.Ad;
import com.idealista.adrankingchallenge.domain.ad.ScoreHandler;

public class AdCompleteScore implements ScoreHandler {

  private static final Integer POINTS_COMPLETE_AD = 40;

  @Override
  public Integer pointsToAdd(Ad ad) {
    return ad.isComplete() ? POINTS_COMPLETE_AD : 0;
  }
}
