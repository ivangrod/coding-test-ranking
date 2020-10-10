package com.idealista.adrankingchallenge.domain.ad;

import com.idealista.adrankingchallenge.domain.ad.search.AdsFound;

public interface AdRepository {

  AdsFound findAdPublicOrderByScore();
}
