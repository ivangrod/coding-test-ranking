package com.idealista.adrankingchallenge.infrastructure.api;

import com.idealista.adrankingchallenge.application.ad.search.AdFound;
import com.idealista.adrankingchallenge.domain.ad.Picture;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;
import org.apache.commons.lang3.StringUtils;

public class QualityAd {

  private Integer id;
  private String typology;
  private String description;
  private List<String> pictureUrls;
  private Integer houseSize;
  private Integer gardenSize;
  private Integer score;
  private String irrelevantSince;

  public QualityAd(Integer id, String typology, String description,
      List<String> pictureUrls, Integer houseSize, Integer gardenSize, Integer score,
      Date irrelevantSince) {
    this.id = id;
    this.typology = typology;
    this.description = description;
    this.pictureUrls = pictureUrls;
    this.houseSize = houseSize;
    this.gardenSize = gardenSize;
    this.score = score;
    this.irrelevantSince =
        (irrelevantSince != null) ? dateToString(irrelevantSince) : StringUtils.EMPTY;
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

  public Integer getScore() {
    return score;
  }

  public String getIrrelevantSince() {
    return irrelevantSince;
  }

  private String dateToString(Date irrelevantSince) {
    ZoneId systemZone = ZoneId.systemDefault();
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM/dd/yyyy - HH:mm:ss");
    return ZonedDateTime.ofInstant(irrelevantSince.toInstant(), systemZone)
                        .format(formatter);
  }

  public static QualityAd buildQualityAdFromAdFound(AdFound adFound) {
    return new QualityAd(adFound.getId(), adFound.getTypology()
                                                 .name(), adFound.getDescription(),
                         adFound.getPictures()
                                .stream()
                                .map(Picture::getUrl)
                                .collect(Collectors.toList()),
                         adFound.getHouseSize(), adFound.getGardenSize(), adFound.getScore(),
                         adFound.getIrrelevantSince());
  }
}
