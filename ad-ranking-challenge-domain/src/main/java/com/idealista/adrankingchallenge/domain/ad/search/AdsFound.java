package com.idealista.adrankingchallenge.domain.ad.search;

import com.idealista.adrankingchallenge.domain.ad.Ad;
import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

public class AdsFound {

  private final List<Ad> ads;

  public AdsFound(List<Ad> ads) {
    Objects.requireNonNull(ads, "ads must not be null");
    this.ads = Collections.unmodifiableList(ads);
  }

  public List<Ad> getAds() {
    return ads;
  }

  public List<Ad> getPublicAds() {
    return ads.stream().filter(Ad::isNotIrrelevant).collect(Collectors.toList());
  }

  public List<Ad> getIrrelevantAds() {
    return ads.stream().filter(Ad::isIrrelevant).collect(Collectors.toList());
  }
}
