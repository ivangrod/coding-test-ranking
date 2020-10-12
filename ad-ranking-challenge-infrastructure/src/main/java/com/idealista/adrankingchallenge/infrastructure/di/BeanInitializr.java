package com.idealista.adrankingchallenge.infrastructure.di;

import com.idealista.adrankingchallenge.application.ad.scoring.AdsScoreCalculator;
import com.idealista.adrankingchallenge.application.ad.search.AdIrrelevantSearcher;
import com.idealista.adrankingchallenge.application.ad.search.AdPublicSearcher;
import com.idealista.adrankingchallenge.domain.ad.AdRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

@Component
public class BeanInitializr {

  @Bean
  public AdPublicSearcher adSearcher(AdRepository adRepository) {
    return new AdPublicSearcher(adRepository);
  }

  @Bean
  public AdIrrelevantSearcher adIrrelevantSearcher(AdRepository adRepository) {
    return new AdIrrelevantSearcher(adRepository);
  }

  @Bean
  public AdsScoreCalculator adsScoreCalculator(AdRepository adRepository) {
    return new AdsScoreCalculator(adRepository);
  }
}
