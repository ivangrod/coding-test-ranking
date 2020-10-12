package com.idealista.adrankingchallenge.infrastructure.persistence;

import java.util.Objects;

public class PictureVO {

  private Integer id;
  private String url;
  private String quality;

  public PictureVO() {
  }

  public PictureVO(Integer id, String url, String quality) {
    this.id = id;
    this.url = url;
    this.quality = quality;
  }

  public Integer getId() {
    return id;
  }

  public String getUrl() {
    return url;
  }

  public String getQuality() {
    return quality;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    PictureVO pictureVO = (PictureVO) o;
    return Objects.equals(id, pictureVO.id) &&
        Objects.equals(url, pictureVO.url) &&
        Objects.equals(quality, pictureVO.quality);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, url, quality);
  }
}
