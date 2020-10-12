package com.idealista.adrankingchallenge.domain.ad.create;

import com.idealista.adrankingchallenge.domain.ad.Ad;
import com.idealista.adrankingchallenge.domain.ad.AdDescription;
import com.idealista.adrankingchallenge.domain.ad.AdId;
import com.idealista.adrankingchallenge.domain.ad.AdSize;
import com.idealista.adrankingchallenge.domain.ad.Picture;
import com.idealista.adrankingchallenge.domain.ad.Picture.Definition;
import com.idealista.adrankingchallenge.domain.ad.Typology;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import org.apache.commons.lang3.RandomStringUtils;
import org.apache.commons.lang3.StringUtils;

public class AdMother {

  public static Ad adEmpty() {
    return createAdFromPrimitivesValues(1, Typology.FLAT, StringUtils.EMPTY, Collections.emptyList(), 0, 0);
  }

  public static Ad adWithoutPictures() {
    return createAdFromPrimitivesValues(1, Typology.FLAT, "Beautiful house", Collections.emptyList(), 0, 0);
  }

  public static Ad adWithAnHDPicture() {
    return createAdFromPrimitivesValues(1, Typology.FLAT, "Beautiful house", Collections
        .singletonList(new Picture(1, "http://www.idealista.com/pictures/1", Definition.HD)), 0, 0);
  }

  public static Ad adWithTwoHDPictures() {
    List<Picture> twoHDPictures = Arrays
        .asList(new Picture(1, "http://www.idealista.com/pictures/1", Definition.HD),
                new Picture(2, "http://www.idealista.com/pictures/2", Definition.HD));
    return createAdFromPrimitivesValues(1, Typology.FLAT, "Beautiful house", twoHDPictures, 0, 0);
  }

  public static Ad adWithASDPicture() {
    return createAdFromPrimitivesValues(1, Typology.FLAT, "Beautiful house", Collections
        .singletonList(new Picture(1, "http://www.idealista.com/pictures/1", Definition.SD)), 0, 0);
  }

  public static Ad adWithASDPictureAndAHDPicture() {
    List<Picture> aHDPictureAndASDPicture = Arrays
        .asList(new Picture(1, "http://www.idealista.com/pictures/1", Definition.HD),
                new Picture(2, "http://www.idealista.com/pictures/2", Definition.SD));
    return createAdFromPrimitivesValues(1, Typology.FLAT, "Beautiful house", aHDPictureAndASDPicture, 0, 0);
  }

  public static Ad adWithDescription() {
    return createAdFromPrimitivesValues(1, Typology.FLAT, "Beautiful house", Collections.emptyList(), 0, 0);
  }

  public static Ad adWithoutDescription() {
    return createAdFromPrimitivesValues(1, Typology.FLAT, "", Collections.emptyList(), 0, 0);
  }

  public static Ad adFlatWithThirtyWordsInTheDescription() {
    return createAdFromPrimitivesValues(1, Typology.FLAT, createDescriptionWithWords(30), Collections.emptyList(),
                                        0, 0);
  }

  public static Ad adFlatWithNineteenWordsInTheDescription() {
    return createAdFromPrimitivesValues(1, Typology.FLAT, createDescriptionWithWords(19), Collections.emptyList(),
                                        0, 0);
  }

  public static Ad adFlatWithFiftyWordsInTheDescription() {
    return createAdFromPrimitivesValues(1, Typology.FLAT, createDescriptionWithWords(50), Collections.emptyList(),
                                        0, 0);
  }

  public static Ad adChaletWithFiftyWordsInTheDescription() {
    return createAdFromPrimitivesValues(1, Typology.CHALET, createDescriptionWithWords(50),
                                        Collections.emptyList(), 0, 0);
  }

  public static Ad adGarageWithDescriptionContainingTheWords(List<String> keywordInDescription) {
    String descriptionWithKeywords = createDescriptionWithWords(20).concat(
        keywordInDescription.stream().collect(Collectors.joining(" ")));
    return createAdFromPrimitivesValues(1, Typology.GARAGE, descriptionWithKeywords, Collections.emptyList()
        , 0, 0);
  }

  public static Ad adFlatComplete() {
    List<Picture> onePicture = Collections
        .singletonList(new Picture(1, "http://www.idealista.com/pictures/1", Definition.HD));
    return createAdFromPrimitivesValues(1, Typology.FLAT, createDescriptionWithWords(10),
                                        onePicture, 85, 0);
  }

  public static Ad adChaletComplete() {
    List<Picture> onePicture = Collections
        .singletonList(new Picture(1, "http://www.idealista.com/pictures/1", Definition.HD));
    return createAdFromPrimitivesValues(1, Typology.CHALET, createDescriptionWithWords(10),
                                        onePicture, 85, 120);
  }

  public static Ad adChaletCompleteWithThreeHDPictures() {
    List<Picture> threeHDPictures = Arrays
        .asList(new Picture(1, "http://www.idealista.com/pictures/1", Definition.HD),
                new Picture(2, "http://www.idealista.com/pictures/2", Definition.HD),
                new Picture(3, "http://www.idealista.com/pictures/3", Definition.HD));
    return createAdFromPrimitivesValues(1, Typology.CHALET, createDescriptionWithWords(50),
                                        threeHDPictures, 85, 120);
  }

  public static Ad adGarageWithDescriptionComplete() {
    List<Picture> onePicture = Collections
        .singletonList(new Picture(1, "http://www.idealista.com/pictures/1", Definition.HD));
    return createAdFromPrimitivesValues(1, Typology.GARAGE, createDescriptionWithWords(10),
                                        onePicture, 0, 0);
  }

  public static Ad adGarageWithoutDescriptionComplete() {
    List<Picture> onePicture = Collections
        .singletonList(new Picture(1, "http://www.idealista.com/pictures/1", Definition.SD));
    return createAdFromPrimitivesValues(1, Typology.GARAGE, StringUtils.EMPTY, onePicture,
                                        0, 0);
  }

  public static Ad adFlatWithHDPictureAndFiftyWordsInTheDescription() {
    List<Picture> aHDPicture = Arrays.asList(new Picture(1, "http://www.idealista.com/pictures/1", Definition.HD));
    return createAdFromPrimitivesValues(1, Typology.FLAT, createDescriptionWithWords(50), aHDPicture, 0, 0);
  }

  private static Ad createAdFromPrimitivesValues(Integer id, Typology typology, String description,
      List<Picture> pictureUrls, Integer houseSize, Integer gardenSize) {
    return Ad.createAd(new AdId(id), typology, new AdDescription(description), pictureUrls, new AdSize(houseSize),
                       new AdSize(gardenSize));
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
