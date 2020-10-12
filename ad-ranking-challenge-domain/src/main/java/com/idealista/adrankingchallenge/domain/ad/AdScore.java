package com.idealista.adrankingchallenge.domain.ad;

import com.idealista.adrankingchallenge.domain.ad.shared.IntValueObject;

public class AdScore extends IntValueObject {

  public static final Integer MAX_SCORE = 100;
  public static final Integer MIN_SCORE = 0;

  public static final Integer IRRELEVANT_THRESHOLD = 40;

  public AdScore(Integer value) {
    super(value != null ? Math.max(MIN_SCORE, Math.min(MAX_SCORE, value)) : 0);
  }

  public boolean isLowerIrrelevantThreshold() {
    return value() < IRRELEVANT_THRESHOLD;
  }

  public boolean isHigherOrEqualIrrelevantThreshold() {
    return value() >= IRRELEVANT_THRESHOLD;
  }
}
