package com.idealista.adrankingchallenge.create;

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
import org.apache.commons.lang3.RandomStringUtils;
import org.apache.commons.lang3.StringUtils;

public class AdMother {

  public static Ad adFlatWithouDescriptionWithAnSDPictureAndIdentifierFrom(Integer identifier) {
    List<Picture> onePicture = Collections
        .singletonList(new Picture(1, "http://www.idealista.com/pictures/1", Definition.SD));
    return createAdFromPrimitivesValues(identifier, Typology.FLAT, StringUtils.EMPTY, onePicture, 0, 0);
  }

  public static Ad adWithTwoHDPicturesAndIdentifierFrom(Integer identifier) {
    List<Picture> twoHDPictures = Arrays
        .asList(new Picture(1, "http://www.idealista.com/pictures/1", Definition.HD),
                new Picture(2, "http://www.idealista.com/pictures/2", Definition.HD));
    return createAdFromPrimitivesValues(identifier, Typology.FLAT, "Beautiful house", twoHDPictures, 0, 0);
  }

  public static Ad adWithoutDescriptionAndIdentifierFrom(Integer identifier) {
    return createAdFromPrimitivesValues(identifier, Typology.FLAT, "", Collections.emptyList(), 0, 0);
  }

  private static Ad createAdFromPrimitivesValues(Integer id, Typology typology, String description,
      List<Picture> pictureUrls, Integer houseSize, Integer gardenSize) {
    return Ad.createAd(new AdId(id), typology, new AdDescription(description), pictureUrls, new AdSize(houseSize),
                       new AdSize(gardenSize));
  }

  private static String createDescriptionWithWords(int numberOfWords) {
    StringBuilder strBuilder = new StringBuilder();
    for (int times = 0; times < numberOfWords; times++) {
      strBuilder = strBuilder.append(RandomStringUtils.randomAlphabetic(5)).append(StringUtils.SPACE);
    }
    return strBuilder.toString();
  }
}
