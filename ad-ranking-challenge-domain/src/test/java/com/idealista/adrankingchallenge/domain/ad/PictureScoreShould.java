package com.idealista.adrankingchallenge.domain.ad;

import com.idealista.adrankingchallenge.domain.ad.scoring.PictureScore;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

public class PictureScoreShould {

  private static final Integer POINTS_WITHOUT_PICTURE = -10;
  private static final Integer POINTS_WITH_A_HD_PICTURE = 20;
  private static final Integer POINTS_WITH_A_SD_PICTURE = 10;

  @Test
  public void return_Minus_Ten_Points_Given_An_Ad_Without_Pictures() {

    //Arrange
    Ad adWithoutPictures = AdMother.adWithoutPictures();

    //Act
    int minusTenPoints = new PictureScore().pointsToAdd(adWithoutPictures);

    //Assert
    Assertions.assertThat(minusTenPoints)
              .isEqualTo(POINTS_WITHOUT_PICTURE);
  }

  @Test
  public void return_Twenty_Points_Given_An_Ad_With_A_HD_Picture() {

    //Arrange
    Ad adWithAnHDPicture = AdMother.adWithAnHDPicture();

    //Act
    int twentyPoints = new PictureScore().pointsToAdd(adWithAnHDPicture);

    //Assert
    Assertions.assertThat(twentyPoints)
              .isEqualTo(POINTS_WITH_A_HD_PICTURE * adWithAnHDPicture.getPictures()
                                                                     .size());
  }

  @Test
  public void return_Forty_Points_Given_An_Ad_With_Two_HD_Pictures() {

    //Arrange
    Ad adWithTwoHDPictures = AdMother.adWithTwoHDPictures();

    //Act
    int fortyPoints = new PictureScore().pointsToAdd(adWithTwoHDPictures);

    //Assert
    Assertions.assertThat(fortyPoints)
              .isEqualTo(POINTS_WITH_A_HD_PICTURE * adWithTwoHDPictures.getPictures()
                                                                       .size());
  }

  @Test
  public void return_Ten_Points_Given_An_Ad_With_A_SD_Picture() {

    //Arrange
    Ad adWithASDPicture = AdMother.adWithASDPicture();

    //Act
    int tenPoints = new PictureScore().pointsToAdd(adWithASDPicture);

    //Assert
    Assertions.assertThat(tenPoints)
              .isEqualTo(POINTS_WITH_A_SD_PICTURE * adWithASDPicture.getPictures()
                                                                    .size());
  }

  @Test
  public void return_Thirty_Points_Given_An_Ad_With_A_SD_Picture_And_A_HD_Picture() {

    //Arrange
    Ad adWithASDPictureAndAHDPicture = AdMother.adWithASDPictureAndAHDPicture();

    int hdPictureCount = Math.toIntExact(
        adWithASDPictureAndAHDPicture.getPictures()
                                     .stream()
                                     .filter(Picture::isHighDefinition)
                                     .count());
    int sdPictureCount = Math.toIntExact(
        adWithASDPictureAndAHDPicture.getPictures()
                                     .stream()
                                     .filter(Picture::isStandardDefinition)
                                     .count());

    //Act
    int thirtyPoints = new PictureScore().pointsToAdd(adWithASDPictureAndAHDPicture);

    //Assert
    Assertions.assertThat(thirtyPoints)
              .isEqualTo(Math.addExact(POINTS_WITH_A_SD_PICTURE * sdPictureCount,
                                       POINTS_WITH_A_HD_PICTURE * hdPictureCount));
  }
}
