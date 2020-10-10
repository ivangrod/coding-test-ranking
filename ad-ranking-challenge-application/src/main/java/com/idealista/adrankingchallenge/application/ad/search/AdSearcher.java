package com.idealista.adrankingchallenge.application.ad.search;

import com.idealista.adrankingchallenge.application.UseCase;
import java.util.Arrays;
import java.util.List;

public class AdSearcher implements UseCase<SearchingAdParams, SearchingAdReturn> {

  @Override
  public SearchingAdReturn execute(SearchingAdParams param) {
    List<AdFound> adsFound = createFakeAdFoundCollectionWithTenAds();
    return new SearchingAdReturn(adsFound);
  }

  // TODO Only for testing purpose -> Repository.
  private List<AdFound> createFakeAdFoundCollectionWithTenAds() {
    List<AdFound> result = Arrays.asList(AdFound.createAdFoundEmpty(), AdFound.createAdFoundEmpty(),
        AdFound.createAdFoundEmpty(), AdFound.createAdFoundEmpty(), AdFound.createAdFoundEmpty(),
        AdFound.createAdFoundEmpty(), AdFound.createAdFoundEmpty(), AdFound.createAdFoundEmpty(),
        AdFound.createAdFoundEmpty(), AdFound.createAdFoundEmpty());

    return result;
  }
}
