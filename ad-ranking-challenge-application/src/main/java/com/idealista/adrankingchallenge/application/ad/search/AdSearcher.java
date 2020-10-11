package com.idealista.adrankingchallenge.application.ad.search;

import com.idealista.adrankingchallenge.domain.ad.AdRepository;
import java.util.Collections;
import java.util.List;

public class AdSearcher {

  private final AdRepository adRepository;

  public AdSearcher(AdRepository adRepository) {
    this.adRepository = adRepository;
  }

  public SearchingAdReturn execute() {
    List<AdFound> adsFound = AdFound.fromAds(adRepository.findAllOrderByScore()
                                                         .getAds()
                                                         .orElse(Collections.emptyList()));
    return new SearchingAdReturn(adsFound);
  }
}
