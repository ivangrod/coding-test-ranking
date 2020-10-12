package com.idealista.adrankingchallenge.acceptance.config;

import com.idealista.adrankingchallenge.infrastructure.persistence.inmemory.InMemoryPersistence;

public class InMemoryPersistenceStub extends InMemoryPersistence {

  public void clear() {
    getAds().clear();
    getPictures().clear();
  }
}