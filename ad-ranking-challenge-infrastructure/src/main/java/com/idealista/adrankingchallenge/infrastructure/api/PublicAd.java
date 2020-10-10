package com.idealista.adrankingchallenge.infrastructure.api;

import com.idealista.adrankingchallenge.application.ad.search.AdFound;
import com.idealista.adrankingchallenge.domain.ad.Picture;
import java.util.List;
import java.util.stream.Collectors;

public class PublicAd {

  private Integer id;
  private String typology;
  private String description;
  private List<String> pictureUrls;
  private Integer houseSize;
  private Integer gardenSize;

  public PublicAd(Integer id, String typology, String description,
      List<String> pictureUrls, Integer houseSize, Integer gardenSize) {
    this.id = id;
    this.typology = typology;
    this.description = description;
    this.pictureUrls = pictureUrls;
    this.houseSize = houseSize;
    this.gardenSize = gardenSize;
  }

  public static PublicAd buildPublicAdFromAdFound(AdFound adFound) {
    return new PublicAd(adFound.getId(), adFound.getTypology(), adFound.getDescription(),
        adFound.getPictures().stream().map(Picture::getUrl).collect(Collectors.toList()),
        adFound.getHouseSize(), adFound.getGardenSize());
  }

  public Integer getId() {
    return id;
  }

  public void setId(Integer id) {
    this.id = id;
  }

  public String getTypology() {
    return typology;
  }

  public void setTypology(String typology) {
    this.typology = typology;
  }

  public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
  }

  public List<String> getPictureUrls() {
    return pictureUrls;
  }

  public void setPictureUrls(List<String> pictureUrls) {
    this.pictureUrls = pictureUrls;
  }

  public Integer getHouseSize() {
    return houseSize;
  }

  public void setHouseSize(Integer houseSize) {
    this.houseSize = houseSize;
  }

  public Integer getGardenSize() {
    return gardenSize;
  }

  public void setGardenSize(Integer gardenSize) {
    this.gardenSize = gardenSize;
  }
}
