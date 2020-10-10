package com.idealista.adrankingchallenge.domain.ad;

import java.util.Collections;
import java.util.List;
import java.util.Objects;

public class Ad {

  private Integer id;
  private String typology;
  private String description;
  private List<String> pictureUrls;
  private Integer houseSize;
  private Integer gardenSize;

  public Ad(Integer id, String typology, String description,
      List<String> pictureUrls, Integer houseSize, Integer gardenSize) {
    this.id = id;
    this.typology = typology;
    this.description = description;
    this.pictureUrls = pictureUrls;
    this.houseSize = houseSize;
    this.gardenSize = gardenSize;
  }

  public Integer getId() {
    return id;
  }

  public String getTypology() {
    return typology;
  }

  public String getDescription() {
    return description;
  }

  public List<String> getPictureUrls() {
    return pictureUrls;
  }

  public Integer getHouseSize() {
    return houseSize;
  }

  public Integer getGardenSize() {
    return gardenSize;
  }

  public static Ad createAdEmpty() {
    return new Ad(0, "", "", Collections.emptyList(), 0, 0);
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
        Objects.equals(typology, ad.typology) &&
        Objects.equals(description, ad.description) &&
        Objects.equals(pictureUrls, ad.pictureUrls) &&
        Objects.equals(houseSize, ad.houseSize) &&
        Objects.equals(gardenSize, ad.gardenSize);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, typology, description, pictureUrls, houseSize, gardenSize);
  }

  @Override
  public String toString() {
    return "Ad{" +
        "id=" + id +
        ", typology='" + typology + '\'' +
        ", description='" + description + '\'' +
        ", pictureUrls=" + pictureUrls +
        ", houseSize=" + houseSize +
        ", gardenSize=" + gardenSize +
        '}';
  }
}
