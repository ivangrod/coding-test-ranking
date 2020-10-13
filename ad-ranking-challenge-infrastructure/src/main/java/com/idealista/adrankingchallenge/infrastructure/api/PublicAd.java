package com.idealista.adrankingchallenge.infrastructure.api;

import com.idealista.adrankingchallenge.application.ad.search.AdFound;
import com.idealista.adrankingchallenge.domain.ad.Picture;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public final class PublicAd {

  private final Integer id;
  private final String typology;
  private final String description;
  private final List<String> pictureUrls;
  private final Integer houseSize;
  private final Integer gardenSize;

  private PublicAd(Integer id, String typology, String description,
      List<String> pictureUrls, Integer houseSize, Integer gardenSize) {
    this.id = id;
    this.typology = typology;
    this.description = description;
    this.pictureUrls = Collections.unmodifiableList(pictureUrls);
    this.houseSize = houseSize;
    this.gardenSize = gardenSize;
  }

  public static PublicAd buildPublicAdFromAdFound(AdFound adFound) {
    List<String> pictureUrls = adFound.getPictures().stream().map(Picture::getUrl).collect(Collectors.toList());
    return new PublicAd(adFound.getId(), adFound.getTypology().name(), adFound.getDescription(),
                        pictureUrls, adFound.getHouseSize(), adFound.getGardenSize());
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
}
