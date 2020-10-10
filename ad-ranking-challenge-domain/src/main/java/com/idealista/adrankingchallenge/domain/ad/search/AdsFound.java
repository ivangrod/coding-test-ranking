package com.idealista.adrankingchallenge.domain.ad.search;

import com.idealista.adrankingchallenge.domain.ad.Ad;
import java.util.Arrays;
import java.util.List;

public class AdsFound {

  private final List<Ad> ads;

  public AdsFound(List<Ad> ads) {
    this.ads = ads;
  }

  public List<Ad> getAds() {
    return ads;
  }

  // TODO Only for testing purpose -> Repository.
  public static AdsFound createFakeAdFoundCollectionWithTenAds() {
    List<Ad> result = Arrays.asList(Ad.createAdEmpty(), Ad.createAdEmpty(),
        Ad.createAdEmpty(), Ad.createAdEmpty(), Ad.createAdEmpty(),
        Ad.createAdEmpty(), Ad.createAdEmpty(), Ad.createAdEmpty(),
        Ad.createAdEmpty(), Ad.createAdEmpty());

    return new AdsFound(result);
  }
}
