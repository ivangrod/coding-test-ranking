package com.idealista.adrankingchallenge.application.ad.search;

import java.util.Collections;
import java.util.List;
import java.util.Objects;

public class AdFound {

  private Integer id;
  private String typology;
  private String description;
  private List<String> pictureUrls;
  private Integer houseSize;
  private Integer gardenSize;

  public AdFound(Integer id, String typology, String description,
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

  public static AdFound createAdFoundEmpty() {
    return new AdFound(0, "", "", Collections.emptyList(), 0, 0);
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
        Objects.equals(pictureUrls, adFound.pictureUrls) &&
        Objects.equals(houseSize, adFound.houseSize) &&
        Objects.equals(gardenSize, adFound.gardenSize);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, typology, description, pictureUrls, houseSize, gardenSize);
  }

  @Override
  public String toString() {
    return "AdFound{" +
        "id=" + id +
        ", typology='" + typology + '\'' +
        ", description='" + description + '\'' +
        ", pictureUrls=" + pictureUrls +
        ", houseSize=" + houseSize +
        ", gardenSize=" + gardenSize +
        '}';
  }
}
