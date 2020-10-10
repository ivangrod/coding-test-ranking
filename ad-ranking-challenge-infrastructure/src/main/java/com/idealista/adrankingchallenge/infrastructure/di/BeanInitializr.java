package com.idealista.adrankingchallenge.infrastructure.di;

import com.idealista.adrankingchallenge.application.UseCase;
import com.idealista.adrankingchallenge.application.ad.search.AdSearcher;
import com.idealista.adrankingchallenge.application.ad.search.SearchingAdParams;
import com.idealista.adrankingchallenge.application.ad.search.SearchingAdReturn;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

@Component
public class BeanInitializr {

  @Bean
  public UseCase<SearchingAdParams, SearchingAdReturn> adSearcher() {
    return new AdSearcher();
  }

}
