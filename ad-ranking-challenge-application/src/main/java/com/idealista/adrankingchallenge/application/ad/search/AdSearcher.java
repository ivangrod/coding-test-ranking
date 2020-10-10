package com.idealista.adrankingchallenge.application.ad.search;

import com.idealista.adrankingchallenge.application.UseCase;
import com.idealista.adrankingchallenge.domain.ad.AdRepository;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class AdSearcher implements UseCase<SearchingAdParams, SearchingAdReturn> {

  private final AdRepository adRepository;

  public AdSearcher(AdRepository adRepository) {
    this.adRepository = adRepository;
  }

  @Override
  public SearchingAdReturn execute(SearchingAdParams param) {
    List<AdFound> adsFound = AdFound.fromAds(adRepository.findAdPublicOrderByRating().getAds());
    return new SearchingAdReturn(adsFound);
  }
}
