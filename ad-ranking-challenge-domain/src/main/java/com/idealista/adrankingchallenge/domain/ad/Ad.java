package com.idealista.adrankingchallenge.domain.ad;

import java.time.Instant;
import java.util.Date;
import java.util.List;
import java.util.Objects;

public final class Ad {

  private final AdId id;
  private final Typology typology;
  private final AdDescription description;
  private final List<Picture> pictures;
  private final AdSize houseSize;
  private final AdSize gardenSize;
  private final AdScore score;
  private final AdDate irrelevantSince;

  private Ad(AdId id, Typology typology, AdDescription description, List<Picture> pictureUrls,
      AdSize houseSize, AdSize gardenSize) {
    this.id = id;
    this.typology = typology;
    this.description = description;
    this.pictures = pictureUrls;
    this.houseSize = houseSize;
    this.gardenSize = gardenSize;
    this.score = new AdScore(0);
    this.irrelevantSince = new AdDate();
  }

  private Ad(AdId id, Typology typology, AdDescription description,
      List<Picture> pictureUrls, AdSize houseSize, AdSize gardenSize, AdScore score,
      AdDate irrelevantSince) {
    this.id = id;
    this.typology = typology;
    this.description = description;
    this.pictures = pictureUrls;
    this.houseSize = houseSize;
    this.gardenSize = gardenSize;
    this.score = score;
    this.irrelevantSince = irrelevantSince;
  }

  public static Ad createAd(AdId id, Typology typology, AdDescription description,
      List<Picture> pictureUrls, AdSize houseSize, AdSize gardenSize) {
    return new Ad(id, typology, description, pictureUrls, houseSize, gardenSize);
  }

  public static Ad of(AdId id, Typology typology, AdDescription description,
      List<Picture> pictureUrls, AdSize houseSize, AdSize gardenSize, AdScore score,
      AdDate irrelevantSince) {
    return new Ad(id, typology, description, pictureUrls, houseSize, gardenSize, score, irrelevantSince);
  }

  public Ad withScore(Integer score) {
    AdScore newAdScore = new AdScore(score);
    AdDate irrelevantSince = new AdDate(newAdScore.isLowerIrrelevantThreshold() ? Date.from(Instant.now()) : null);
    return new Ad(this.id, this.typology, this.description, this.pictures, this.houseSize,
                  this.gardenSize, newAdScore, irrelevantSince);
  }

  public AdId getId() {
    return id;
  }

  public Typology getTypology() {
    return typology;
  }

  public AdDescription getDescription() {
    return description;
  }

  public List<Picture> getPictures() {
    return pictures;
  }

  public AdSize getHouseSize() {
    return houseSize;
  }

  public AdSize getGardenSize() {
    return gardenSize;
  }

  public AdScore getScore() {
    return score;
  }

  public AdDate getIrrelevantSince() {
    return irrelevantSince;
  }

  public boolean isIrrelevant() {
    return this.score.isLowerIrrelevantThreshold();
  }

  public boolean isNotIrrelevant() {
    return this.score.isHigherOrEqualIrrelevantThreshold();
  }

  public Ad updateScore(List<ScoreHandler> scoreHandlers) {
    Integer newScore = Integer.valueOf(0);
    for (ScoreHandler scoreHandler : scoreHandlers) {
      newScore += scoreHandler.pointsToAdd(this);
    }
    return withScore(newScore);
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    Ad ad = (Ad) o;
    return Objects.equals(id, ad.id) &&
        typology == ad.typology &&
        Objects.equals(description, ad.description) &&
        Objects.equals(pictures, ad.pictures) &&
        Objects.equals(houseSize, ad.houseSize) &&
        Objects.equals(gardenSize, ad.gardenSize) &&
        Objects.equals(score, ad.score) &&
        Objects.equals(irrelevantSince, ad.irrelevantSince);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, typology, description, pictures, houseSize, gardenSize, score, irrelevantSince);
  }

  @Override
  public String toString() {
    return "Ad{" +
        "id=" + id +
        ", typology=" + typology +
        ", description=" + description +
        ", pictures=" + pictures +
        ", houseSize=" + houseSize +
        ", gardenSize=" + gardenSize +
        ", score=" + score +
        ", irrelevantSince=" + irrelevantSince +
        '}';
  }

  public boolean isAFlatWithMediumDescription() {
    return this.typology.equals(Typology.FLAT) && description.isMedium();
  }

  public boolean isAFlatWithLongDescription() {
    return this.typology.equals(Typology.FLAT) && description.isLong();
  }

  public boolean isAChaletWithLongDescription() {
    return this.typology.equals(Typology.CHALET) && description.isLong();
  }

  public boolean hasPhoto() {
    return !this.pictures.isEmpty();
  }

  public boolean isComplete() {

    boolean isComplete = false;
    switch (this.typology) {
      case FLAT:
        isComplete = description.hasDescription() && hasPhoto() && houseSize.hasSize();
        break;
      case CHALET:
        isComplete = description.hasDescription() && hasPhoto() && houseSize.hasSize() && gardenSize.hasSize();
        break;
      case GARAGE:
        isComplete = hasPhoto();
    }
    return isComplete;
  }
}

