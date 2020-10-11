package com.idealista.adrankingchallenge.application.ad.search;

import com.idealista.adrankingchallenge.domain.ad.Ad;
import com.idealista.adrankingchallenge.domain.ad.Picture;
import com.idealista.adrankingchallenge.domain.ad.Typology;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class AdFound {

  private Integer id;
  private Typology typology;
  private String description;
  private List<Picture> pictures;
  private Integer houseSize;
  private Integer gardenSize;

  public AdFound(Integer id, Typology typology, String description,
      List<Picture> pictures, Integer houseSize, Integer gardenSize) {
    this.id = id;
    this.typology = typology;
    this.description = description;
    this.pictures = pictures;
    this.houseSize = houseSize;
    this.gardenSize = gardenSize;
  }

  public static List<AdFound> fromAds(List<Ad> ads) {
    return ads.stream()
              .map(ad -> new AdFound(ad.getId(), ad.getTypology(), ad.getDescription(),
                                     ad.getPictures(), ad.getHouseSize(), ad.getGardenSize()))
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
        Objects.equals(typology, adFound.typology) &&
        Objects.equals(description, adFound.description) &&
        Objects.equals(pictures, adFound.pictures) &&
        Objects.equals(houseSize, adFound.houseSize) &&
        Objects.equals(gardenSize, adFound.gardenSize);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, typology, description, pictures, houseSize, gardenSize);
  }

  @Override
  public String toString() {
    return "AdFound{" +
        "id=" + id +
        ", typology='" + typology + '\'' +
        ", description='" + description + '\'' +
        ", pictureUrls=" + pictures +
        ", houseSize=" + houseSize +
        ", gardenSize=" + gardenSize +
        '}';
  }
}
