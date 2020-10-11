package com.idealista.adrankingchallenge.application.ad.scoring;

import com.idealista.adrankingchallenge.domain.ad.Ad;
import com.idealista.adrankingchallenge.domain.ad.AdRepository;
import com.idealista.adrankingchallenge.domain.ad.ScoreHandler;
import com.idealista.adrankingchallenge.domain.ad.scoring.AdCompleteScore;
import com.idealista.adrankingchallenge.domain.ad.scoring.DescriptionScore;
import com.idealista.adrankingchallenge.domain.ad.scoring.PictureScore;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class AdsScoreCalculator {

  private final AdRepository adRepository;

  private final List<ScoreHandler> scoreHandlers;

  public AdsScoreCalculator(AdRepository adRepository) {
    this.adRepository = adRepository;
    this.scoreHandlers = Arrays
        .asList(new PictureScore(), new DescriptionScore(), new AdCompleteScore());
  }

  public void execute() {
    List<Ad> adsToCalculateScore = adRepository.findAllOrderByScore()
                                               .getAds()
                                               .orElse(Collections.emptyList());
    adsToCalculateScore.forEach(ad -> {
      Ad adWithStoreUpdated = ad.updateScore(scoreHandlers);
      adRepository.save(adWithStoreUpdated);
    });
  }
}
