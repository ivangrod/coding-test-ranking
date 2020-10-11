package com.idealista.adrankingchallenge.domain.ad;

import com.idealista.adrankingchallenge.domain.ad.search.AdsFound;
import java.util.Optional;

public interface AdRepository {

  AdsFound findAllOrderByScore();

  void save(Ad ad);

  Ad findById(Integer identifier);
}
