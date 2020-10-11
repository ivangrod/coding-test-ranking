package com.idealista.adrankingchallenge.domain.ad.search;

import com.idealista.adrankingchallenge.domain.ad.Ad;
import java.util.List;
import java.util.Optional;

public class AdsFound {

  private final List<Ad> ads;

  public AdsFound(List<Ad> ads) {
    this.ads = ads;
  }

  public Optional<List<Ad>> getAds() {
    return Optional.ofNullable(ads);
  }
}
