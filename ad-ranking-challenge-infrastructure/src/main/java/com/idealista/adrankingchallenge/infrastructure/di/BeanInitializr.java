package com.idealista.adrankingchallenge.infrastructure.di;

import com.idealista.adrankingchallenge.application.ad.search.AdSearcher;
import com.idealista.adrankingchallenge.domain.ad.AdRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

@Component
public class BeanInitializr {

  @Bean
  public AdSearcher adSearcher(AdRepository inMemoryPersistence) {
    return new AdSearcher(inMemoryPersistence);
  }
}
