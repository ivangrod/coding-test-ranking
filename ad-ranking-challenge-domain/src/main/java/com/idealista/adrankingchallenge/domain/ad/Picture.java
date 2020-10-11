package com.idealista.adrankingchallenge.domain.ad;

import java.util.Objects;

public final class Picture {

  private final Integer id;
  private final String url;
  private final Definition definition;

  public Picture(Integer id, String url, Definition definition) {
    this.id = id;
    this.url = url;
    this.definition = definition;
  }

  public Integer getId() {
    return id;
  }

  public String getUrl() {
    return url;
  }

  public Definition getDefinition() {
    return definition;
  }

  public boolean isHighDefinition() {
    return definition.equals(Definition.HD);
  }

  public boolean isStandardDefinition() {
    return definition.equals(Definition.SD);
  }

  public enum Definition {
    HD, SD;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    Picture picture = (Picture) o;
    return Objects.equals(id, picture.id) &&
        Objects.equals(url, picture.url) &&
        definition == picture.definition;
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, url, definition);
  }

  @Override
  public String toString() {
    return "Picture{" +
        "id=" + id +
        ", url='" + url + '\'' +
        ", definition=" + definition +
        '}';
  }
}
