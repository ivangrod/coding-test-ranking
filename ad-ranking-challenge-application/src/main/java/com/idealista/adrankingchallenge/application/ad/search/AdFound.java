package com.idealista.adrankingchallenge.application.ad.search;

import com.idealista.adrankingchallenge.domain.ad.Ad;
import com.idealista.adrankingchallenge.domain.ad.Picture;
import com.idealista.adrankingchallenge.domain.ad.Typology;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public final class AdFound {

  private final Integer id;
  private final Typology typology;
  private final String description;
  private final List<Picture> pictures;
  private final Integer houseSize;
  private final Integer gardenSize;
  private final Integer score;
  private final Date irrelevantSince;

  private AdFound(Integer id, Typology typology, String description,
      List<Picture> pictures, Integer houseSize, Integer gardenSize, Integer score,
      Date irrelevantSince) {
    this.id = id;
    this.typology = typology;
    this.description = description;
    this.pictures = Collections.unmodifiableList(pictures);
    this.houseSize = houseSize;
    this.gardenSize = gardenSize;
    this.score = score;
    this.irrelevantSince = irrelevantSince;
  }

  public static AdFound fromAd(Ad ad) {
    return new AdFound(ad.getId().value(), ad.getTypology(), ad.getDescription().value(),
                       ad.getPictures(), ad.getHouseSize().value(), ad.getGardenSize().value(),
                       ad.getScore().value(), ad.getIrrelevantSince().toDate());
  }

  public static List<AdFound> fromAds(List<Ad> ads) {
    return ads.stream()
              .map(AdFound::fromAd)
              .collect(Collectors.toList());
  }

  public Integer getId() {
    return id;
  }

  public Typology getTypology() {
    return typology;
  }

  public String getDescription() {
    return description;
  }

  public List<Picture> getPictures() {
    return pictures;
  }

  public Integer getHouseSize() {
    return houseSize;
  }

  public Integer getGardenSize() {
    return gardenSize;
  }

  public Integer getScore() {
    return score;
  }

  public Date getIrrelevantSince() {
    return irrelevantSince;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    AdFound adFound = (AdFound) o;
    return Objects.equals(id, adFound.id) &&
        typology == adFound.typology &&
        Objects.equals(description, adFound.description) &&
        Objects.equals(pictures, adFound.pictures) &&
        Objects.equals(houseSize, adFound.houseSize) &&
        Objects.equals(gardenSize, adFound.gardenSize) &&
        Objects.equals(score, adFound.score) &&
        Objects.equals(irrelevantSince, adFound.irrelevantSince);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, typology, description, pictures, houseSize, gardenSize, score,
                        irrelevantSince);
  }

  @Override
  public String toString() {
    return "AdFound{" +
        "id=" + id +
        ", typology=" + typology +
        ", description='" + description + '\'' +
        ", pictures=" + pictures +
        ", houseSize=" + houseSize +
        ", gardenSize=" + gardenSize +
        ", score=" + score +
        ", irrelevantSince=" + irrelevantSince +
        '}';
  }
}
