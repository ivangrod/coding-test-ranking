package com.idealista.adrankingchallenge.domain.ad;

import com.idealista.adrankingchallenge.domain.ad.shared.IntValueObject;

public class AdSize extends IntValueObject {

  public AdSize(Integer value) {
    super(value);
  }

  public boolean hasSize() {
    return !this.value().equals(0);
  }
}
