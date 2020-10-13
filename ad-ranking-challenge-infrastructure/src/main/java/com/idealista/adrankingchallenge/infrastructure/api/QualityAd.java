package com.idealista.adrankingchallenge.infrastructure.api;

import com.idealista.adrankingchallenge.application.ad.search.AdFound;
import com.idealista.adrankingchallenge.domain.ad.Picture;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;
import org.apache.commons.lang3.StringUtils;

public final class QualityAd {

  private final Integer id;
  private final String typology;
  private final String description;
  private final List<String> pictureUrls;
  private final Integer houseSize;
  private final Integer gardenSize;
  private final Integer score;
  private final String irrelevantSince;

  private QualityAd(Integer id, String typology, String description,
      List<String> pictureUrls, Integer houseSize, Integer gardenSize, Integer score,
      Date irrelevantSince) {
    this.id = id;
    this.typology = typology;
    this.description = description;
    this.pictureUrls = Collections.unmodifiableList(pictureUrls);
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
    List<String> pictureUrls = adFound.getPictures()
                                      .stream()
                                      .map(Picture::getUrl)
                                      .collect(Collectors.toList());
    return new QualityAd(adFound.getId(), adFound.getTypology().name(), adFound.getDescription(),
                         pictureUrls, adFound.getHouseSize(), adFound.getGardenSize(), adFound.getScore(),
                         adFound.getIrrelevantSince());
  }
}
