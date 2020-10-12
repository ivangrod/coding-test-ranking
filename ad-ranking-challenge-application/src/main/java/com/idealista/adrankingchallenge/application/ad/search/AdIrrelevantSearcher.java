package com.idealista.adrankingchallenge.application.ad.search;

import com.idealista.adrankingchallenge.domain.ad.AdRepository;
import java.util.List;

public class AdIrrelevantSearcher {

  private final AdRepository adRepository;

  public AdIrrelevantSearcher(AdRepository adRepository) {
    this.adRepository = adRepository;
  }

  public SearchingAdReturn execute() {
    List<AdFound> adsFound = AdFound.fromAds(adRepository.findAllOrderByScore().getIrrelevantAds());
    return new SearchingAdReturn(adsFound);
  }
}
