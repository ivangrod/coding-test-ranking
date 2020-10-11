package com.idealista.adrankingchallenge.infrastructure.persistence;

import java.util.Date;
import java.util.List;
import java.util.Objects;

public class AdVO {

  private Integer id;
  private String typology;
  private String description;
  private List<Integer> pictures;
  private Integer houseSize;
  private Integer gardenSize;
  private Integer score;
  private Date irrelevantSince;

  public AdVO() {
  }

  public AdVO(Integer id, String typology, String description, List<Integer> pictures,
      Integer houseSize, Integer gardenSize, Integer score, Date irrelevantSince) {
    this.id = id;
    this.typology = typology;
    this.description = description;
    this.pictures = pictures;
    this.houseSize = houseSize;
    this.gardenSize = gardenSize;
    this.score = score;
    this.irrelevantSince = irrelevantSince;
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

  public List<Integer> getPictures() {
    return pictures;
  }

  public void setPictures(List<Integer> pictures) {
    this.pictures = pictures;
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

  public Integer getScore() {
    return score;
  }

  public void setScore(Integer score) {
    this.score = score;
  }

  public Date getIrrelevantSince() {
    return irrelevantSince;
  }

  public void setIrrelevantSince(Date irrelevantSince) {
    this.irrelevantSince = irrelevantSince;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    AdVO adVO = (AdVO) o;
    return Objects.equals(id, adVO.id) &&
        Objects.equals(typology, adVO.typology) &&
        Objects.equals(description, adVO.description) &&
        Objects.equals(pictures, adVO.pictures) &&
        Objects.equals(houseSize, adVO.houseSize) &&
        Objects.equals(gardenSize, adVO.gardenSize) &&
        Objects.equals(score, adVO.score) &&
        Objects.equals(irrelevantSince, adVO.irrelevantSince);
  }

  @Override
  public int hashCode() {
    return Objects
        .hash(id, typology, description, pictures, houseSize, gardenSize, score,
              irrelevantSince);
  }
}
