package com.idealista.adrankingchallenge.application.ad.search;

import java.util.Collections;
import java.util.List;

public final class SearchingAdReturn {

  private final List<AdFound> ads;

  public SearchingAdReturn(List<AdFound> ads) {
    this.ads = Collections.unmodifiableList(ads);
  }

  public List<AdFound> getAds() {
    return ads;
  }
}
