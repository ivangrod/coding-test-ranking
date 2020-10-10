package com.idealista.adrankingchallenge.domain.ad;

import com.idealista.adrankingchallenge.domain.ad.Picture.Definition;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class AdMother {

  public static Ad adInitial() {
    return new Ad(1, "FLAT", "Beautiful house", Collections.emptyList(), 0, 0);
  }

  public static Ad adWithoutPictures() {
    return new Ad(1, "FLAT", "Beautiful house", Collections.emptyList(), 0, 0);
  }

  public static Ad adWithAnHDPicture() {
    return new Ad(1, "FLAT", "Beautiful house", Collections
        .singletonList(new Picture(1, "http://www.idealista.com/pictures/1", Definition.HD)), 0, 0);
  }

  public static Ad adWithTwoHDPictures() {

    List<Picture> twoHDPictures = Arrays
        .asList(new Picture(1, "http://www.idealista.com/pictures/1", Definition.HD),
            new Picture(2, "http://www.idealista.com/pictures/2", Definition.HD));

    return new Ad(1, "FLAT", "Beautiful house", twoHDPictures, 0, 0);
  }

  public static Ad adWithASDPicture() {
    return new Ad(1, "FLAT", "Beautiful house", Collections
        .singletonList(new Picture(1, "http://www.idealista.com/pictures/1", Definition.SD)), 0, 0);
  }

  public static Ad adWithASDPictureAndAHDPicture() {
    List<Picture> aHDPictureAndASDPicture = Arrays
        .asList(new Picture(1, "http://www.idealista.com/pictures/1", Definition.HD),
            new Picture(2, "http://www.idealista.com/pictures/2", Definition.SD));

    return new Ad(1, "FLAT", "Beautiful house", aHDPictureAndASDPicture, 0, 0);
  }

  public static Ad adWithDescription() {
    return new Ad(1, "FLAT", "Beautiful house", Collections.emptyList(), 0, 0);
  }

  public static Ad adWithoutDescription() {
    return new Ad(1, "FLAT", "", Collections.emptyList(), 0, 0);
  }
}
