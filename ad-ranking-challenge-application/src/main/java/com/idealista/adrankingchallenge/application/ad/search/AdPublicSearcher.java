package com.idealista.adrankingchallenge.application.ad.search;

import com.idealista.adrankingchallenge.domain.ad.AdRepository;
import java.util.List;

public class AdPublicSearcher {

  private final AdRepository adRepository;

  public AdPublicSearcher(AdRepository adRepository) {
    this.adRepository = adRepository;
  }

  public SearchingAdReturn execute() {
    List<AdFound> adsFound = AdFound.fromAds(adRepository.findAllOrderByScore().getPublicAds());
    return new SearchingAdReturn(adsFound);
  }
}
