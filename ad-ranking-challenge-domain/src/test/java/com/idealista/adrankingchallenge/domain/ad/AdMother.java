package com.idealista.adrankingchallenge.domain.ad;

import com.idealista.adrankingchallenge.domain.ad.Picture.Definition;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import org.apache.commons.lang3.RandomStringUtils;
import org.apache.commons.lang3.StringUtils;

public class AdMother {

  public static Ad adEmpty() {
    return new Ad(1, Typology.FLAT, StringUtils.EMPTY, Collections.emptyList(), 0, 0);
  }

  public static Ad adWithoutPictures() {
    return new Ad(1, Typology.FLAT, "Beautiful house", Collections.emptyList(), 0, 0);
  }

  public static Ad adWithAnHDPicture() {
    return new Ad(1, Typology.FLAT, "Beautiful house", Collections
        .singletonList(new Picture(1, "http://www.idealista.com/pictures/1", Definition.HD)), 0, 0);
  }

  public static Ad adWithTwoHDPictures() {

    List<Picture> twoHDPictures = Arrays
        .asList(new Picture(1, "http://www.idealista.com/pictures/1", Definition.HD),
                new Picture(2, "http://www.idealista.com/pictures/2", Definition.HD));

    return new Ad(1, Typology.FLAT, "Beautiful house", twoHDPictures, 0, 0);
  }

  public static Ad adWithASDPicture() {
    return new Ad(1, Typology.FLAT, "Beautiful house", Collections
        .singletonList(new Picture(1, "http://www.idealista.com/pictures/1", Definition.SD)), 0, 0);
  }

  public static Ad adWithASDPictureAndAHDPicture() {
    List<Picture> aHDPictureAndASDPicture = Arrays
        .asList(new Picture(1, "http://www.idealista.com/pictures/1", Definition.HD),
                new Picture(2, "http://www.idealista.com/pictures/2", Definition.SD));

    return new Ad(1, Typology.FLAT, "Beautiful house", aHDPictureAndASDPicture, 0, 0);
  }

  public static Ad adWithDescription() {
    return new Ad(1, Typology.FLAT, "Beautiful house", Collections.emptyList(), 0, 0);
  }

  public static Ad adWithoutDescription() {
    return new Ad(1, Typology.FLAT, "", Collections.emptyList(), 0, 0);
  }

  public static Ad adFlatWithThirtyWordsInTheDescription() {
    return new Ad(1, Typology.FLAT, createDescriptionWithWords(30), Collections.emptyList(),
                  0, 0);
  }

  public static Ad adFlatWithNineteenWordsInTheDescription() {
    return new Ad(1, Typology.FLAT, createDescriptionWithWords(19), Collections.emptyList(),
                  0, 0);
  }

  public static Ad adFlatWithFiftyWordsInTheDescription() {
    return new Ad(1, Typology.FLAT, createDescriptionWithWords(50), Collections.emptyList(),
                  0, 0);
  }

  public static Ad adChaletWithFiftyWordsInTheDescription() {
    return new Ad(1, Typology.CHALET, createDescriptionWithWords(50), Collections.emptyList(),
                  0, 0);
  }

  public static Ad adGarageWithDescriptionContainingTheWords(List<String> keywordInDescription) {
    return new Ad(1, Typology.GARAGE,
                  createDescriptionWithWords(20)
                      .concat(keywordInDescription.stream()
                                                  .collect(Collectors.joining(" "))),
                  Collections.emptyList(),
                  0, 0);
  }

  public static Ad adFlatComplete() {
    return new Ad(1, Typology.FLAT, createDescriptionWithWords(10), Collections
        .singletonList(new Picture(1, "http://www.idealista.com/pictures/1", Definition.HD)),
                  85, 0);
  }

  public static Ad adChaletComplete() {
    return new Ad(1, Typology.CHALET, createDescriptionWithWords(10), Collections
        .singletonList(new Picture(1, "http://www.idealista.com/pictures/1", Definition.HD)),
                  85, 120);
  }

  public static Ad adChaletCompleteWithThreeHDPictures() {

    List<Picture> threeHDPictures = Arrays
        .asList(new Picture(1, "http://www.idealista.com/pictures/1", Definition.HD),
                new Picture(2, "http://www.idealista.com/pictures/2", Definition.HD),
                new Picture(3, "http://www.idealista.com/pictures/3", Definition.HD));
    return new Ad(1, Typology.CHALET, createDescriptionWithWords(50),
                  threeHDPictures,85, 120);
  }

  public static Ad adGarageWithDescriptionComplete() {
    return new Ad(1, Typology.GARAGE, createDescriptionWithWords(10), Collections
        .singletonList(new Picture(1, "http://www.idealista.com/pictures/1", Definition.HD)),
                  0, 0);
  }

  public static Ad adGarageWithoutDescriptionComplete() {
    return new Ad(1, Typology.GARAGE, StringUtils.EMPTY, Collections
        .singletonList(new Picture(1, "http://www.idealista.com/pictures/1", Definition.SD)),
                  0, 0);
  }

  public static Ad adFlatWithHDPictureAndFiftyWordsInTheDescription() {
    List<Picture> aHDPicture = Arrays
        .asList(new Picture(1, "http://www.idealista.com/pictures/1", Definition.HD));
    return new Ad(1, Typology.FLAT, createDescriptionWithWords(50), aHDPicture, 0, 0);
  }

  private static String createDescriptionWithWords(int numberOfWords) {
    StringBuilder strBuilder = new StringBuilder();
    for (int times = 0; times < numberOfWords; times++) {
      strBuilder = strBuilder.append(RandomStringUtils.randomAlphabetic(5))
                             .append(StringUtils.SPACE);
    }
    return strBuilder.toString();
  }
}
