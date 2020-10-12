package com.idealista.adrankingchallenge.domain.ad;

import java.text.Collator;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Objects;
import java.util.StringTokenizer;
import org.apache.commons.lang3.StringUtils;

public final class Ad {

  private final static Integer MINIMUN_WORDS_MEDIUM_DESCRIPTION = 20;
  private final static Integer MAXIMUN_WORDS_MEDIUM_DESCRIPTION = 49;

  private static final Integer MAX_SCORE = 100;
  private static final Integer MIN_SCORE = 0;

  private final DescriptionKeywords descriptionKeywords = new DescriptionKeywords(
      new String[]{"Luminoso", "Nuevo", "Céntrico", "Reformado", "Ático"});

  private final Integer id;
  private final Typology typology;
  private final String description;
  private final List<Picture> pictures;
  private final Integer houseSize;
  private final Integer gardenSize;
  private final Integer score;
  private final Date irrelevantSince;

  private Ad(Integer id, Typology typology, String description,
      List<Picture> pictureUrls, Integer houseSize, Integer gardenSize) {
    this.id = id;
    this.typology = typology;
    this.description = description;
    this.pictures = pictureUrls;
    this.houseSize = houseSize;
    this.gardenSize = gardenSize;
    this.score = 0;
    this.irrelevantSince = null;
  }

  private Ad(Integer id, Typology typology, String description,
      List<Picture> pictureUrls, Integer houseSize, Integer gardenSize, Integer score,
      Date irrelevantSince) {
    this.id = id;
    this.typology = typology;
    this.description = description;
    this.pictures = pictureUrls;
    this.houseSize = houseSize;
    this.gardenSize = gardenSize;
    this.score = (score != null) ? Math.max(MIN_SCORE, Math.min(MAX_SCORE, score)) : 0;
    this.irrelevantSince = irrelevantSince;
  }

  public static Ad createAd(Integer id, Typology typology, String description,
      List<Picture> pictureUrls, Integer houseSize,
      Integer gardenSize) {
    return new Ad(id, typology, description, pictureUrls, houseSize, gardenSize);
  }

  public static Ad of(Integer id, Typology typology, String description,
      List<Picture> pictureUrls, Integer houseSize, Integer gardenSize, Integer score,
      Date irrelevantSince) {
    return new Ad(id, typology, description, pictureUrls, houseSize, gardenSize, score,
                  irrelevantSince);
  }

  public Ad withScore(Integer score) {
    return new Ad(this.id, this.typology, this.description, this.pictures, this.houseSize,
                  this.gardenSize, score, this.irrelevantSince);
  }

  public Integer getId() {
    return id;
  }

  public Typology getTypology() {
    return typology;
  }

  public String getDescription() {
    return description;
  }

  public List<Picture> getPictures() {
    return pictures;
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

  public Date getIrrelevantSince() {
    return irrelevantSince;
  }


  public boolean isIrrelevant() {
    return this.score < 40;
  }

  public boolean isNotIrrelevant() {
    return this.score >= 40;
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
    return Objects.equals(descriptionKeywords, ad.descriptionKeywords) &&
        Objects.equals(id, ad.id) &&
        typology == ad.typology &&
        Objects.equals(description, ad.description) &&
        Objects.equals(pictures, ad.pictures) &&
        Objects.equals(houseSize, ad.houseSize) &&
        Objects.equals(gardenSize, ad.gardenSize) &&
        Objects.equals(score, ad.score) &&
        Objects.equals(irrelevantSince, ad.irrelevantSince);
  }

  @Override
  public int hashCode() {
    return Objects
        .hash(descriptionKeywords, id, typology, description, pictures, houseSize, gardenSize,
              score, irrelevantSince);
  }

  @Override
  public String toString() {
    return "Ad{" +
        "descriptionKeywords=" + descriptionKeywords +
        ", id=" + id +
        ", typology=" + typology +
        ", description='" + description + '\'' +
        ", pictures=" + pictures +
        ", houseSize=" + houseSize +
        ", gardenSize=" + gardenSize +
        ", score=" + score +
        ", irrelevantSince=" + irrelevantSince +
        '}';
  }

  public Ad updateScore(List<ScoreHandler> scoreHandlers) {
    Integer newScore = this.score;
    for (ScoreHandler scoreHandler : scoreHandlers) {
      newScore += scoreHandler.pointsToAdd(this);
    }
    return withScore(newScore);
  }

  public boolean isAFlatWithMediumDescription() {
    return this.typology.equals(Typology.FLAT) && hasMediumDescription();
  }

  public boolean isAFlatWithLongDescription() {
    return this.typology.equals(Typology.FLAT) && hasLongDescription();
  }

  public boolean isAChaletWithLongDescription() {
    return this.typology.equals(Typology.CHALET) && hasLongDescription();
  }

  public boolean hasDescription() {
    return StringUtils.isNotBlank(this.description);
  }

  private boolean hasMediumDescription() {
    if (!hasDescription()) {
      return false;
    }
    int words = new StringTokenizer(this.description).countTokens();
    return words >= MINIMUN_WORDS_MEDIUM_DESCRIPTION && words <= MAXIMUN_WORDS_MEDIUM_DESCRIPTION;
  }

  private boolean hasLongDescription() {
    if (!hasDescription()) {
      return false;
    }
    int words = new StringTokenizer(this.description).countTokens();
    return words > MAXIMUN_WORDS_MEDIUM_DESCRIPTION;
  }

  public boolean hasPhoto() {
    return !this.pictures.isEmpty();
  }

  public boolean hasHouseSize() {
    return !this.houseSize.equals(0);
  }

  public boolean hasGardenSize() {
    return !this.gardenSize.equals(0);
  }

  public boolean isComplete() {

    boolean isComplete = false;
    switch (this.typology) {
      case FLAT:
        isComplete = hasDescription() && hasPhoto() && hasHouseSize();
      case CHALET:
        isComplete = hasDescription() && hasPhoto() && hasHouseSize() && hasGardenSize();
      case GARAGE:
        isComplete = hasPhoto();
    }
    return isComplete;
  }

  public Integer numberOfOccurrencesWithKeywordsInTheDescription() {
    if (!hasDescription()) {
      return 0;
    }
    return Math.toIntExact(Arrays.stream(this.description.split(StringUtils.SPACE))
                                 .filter(descriptionWords ->
                                             descriptionKeywords.contains(descriptionWords))
                                 .distinct()
                                 .count());
  }

  private class DescriptionKeywords extends ArrayList<String> {

    private final Collator instance = Collator.getInstance();

    public DescriptionKeywords(String[] keywords) {
      super(Arrays.asList(keywords));
      instance.setStrength(Collator.PRIMARY);
    }

    @Override
    public boolean contains(Object word) {
      String paramWord = (String) word;
      for (String keyword : this) {
        if (instance.compare(keyword, paramWord) == 0) {
          return true;
        }
      }
      return false;
    }
  }
}
