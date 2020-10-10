package com.idealista.adrankingchallenge.application.ad.search;

import java.util.List;

public class SearchingAdReturn {

  private final List<AdFound> ads;

  public SearchingAdReturn(List<AdFound> ads) {
    this.ads = ads;
  }

  public List<AdFound> getAds() {
    return ads;
  }
}
