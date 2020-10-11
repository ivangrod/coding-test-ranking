package com.idealista.adrankingchallenge.domain.ad;

import com.idealista.adrankingchallenge.domain.ad.search.AdsFound;

public interface AdRepository {

  AdsFound findAllOrderByScore();

  void save(Ad ad);

  Ad findById(Integer identifier);
}
