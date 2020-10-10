package com.idealista.adrankingchallenge.domain.ad;

public final class Picture {

  private final Integer id;
  private final String url;
  private final Definition definition;

  public Picture(Integer id, String url, Definition definition) {
    this.id = id;
    this.url = url;
    this.definition = definition;
  }

  public String getUrl() {
    return url;
  }

  public boolean isHighDefinition() {
    return definition.equals(Definition.HD);
  }

  public boolean isStandardDefinition() {
    return definition.equals(Definition.SD);
  }

  enum Definition {
    HD, SD;
  }
}
