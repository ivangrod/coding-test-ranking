package com.idealista.adrankingchallenge.application.ad.scoring;

import com.idealista.adrankingchallenge.application.ad.search.SearchingAdReturn;
import com.idealista.adrankingchallenge.domain.ad.Ad;
import com.idealista.adrankingchallenge.domain.ad.AdRepository;
import java.util.List;

public class AdsScoreCalculator {

  private final AdRepository adRepository;

  public AdsScoreCalculator(AdRepository adRepository) {
    this.adRepository = adRepository;
  }

  public void execute() {
    List<Ad> adsToCalculateScore = adRepository.findAdPublicOrderByScore().getAds();


  }
}
