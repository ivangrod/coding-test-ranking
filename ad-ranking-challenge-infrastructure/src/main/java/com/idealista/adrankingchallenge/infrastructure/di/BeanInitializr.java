package com.idealista.adrankingchallenge.infrastructure.di;

import com.idealista.adrankingchallenge.application.ad.scoring.AdsScoreCalculator;
import com.idealista.adrankingchallenge.application.ad.search.AdSearcher;
import com.idealista.adrankingchallenge.domain.ad.AdRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

@Component
public class BeanInitializr {

  @Bean
  public AdSearcher adSearcher(AdRepository adRepository) {
    return new AdSearcher(adRepository);
  }

  @Bean
  public AdsScoreCalculator adsScoreCalculator(AdRepository adRepository) {
    return new AdsScoreCalculator(adRepository);
  }
}
