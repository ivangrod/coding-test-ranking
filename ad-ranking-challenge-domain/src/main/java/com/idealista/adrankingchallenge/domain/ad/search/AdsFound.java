package com.idealista.adrankingchallenge.domain.ad.search;

import com.idealista.adrankingchallenge.domain.ad.Ad;
import java.util.List;

public class AdsFound {

  private final List<Ad> ads;

  public AdsFound(List<Ad> ads) {
    this.ads = ads;
  }

  public List<Ad> getAds() {
    return ads;
  }
}
