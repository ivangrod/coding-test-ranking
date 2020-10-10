package com.idealista.adrankingchallenge.domain.ad;

import com.idealista.adrankingchallenge.domain.ad.scoring.DescriptionScore;
import com.idealista.adrankingchallenge.domain.ad.scoring.PictureScore;
import java.util.Arrays;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

public class AdShould {

  private static final Integer POINTS_WITHOUT_PICTURE = -10;
  private static final Integer POINTS_WITH_A_HD_PICTURE = 20;
  private static final Integer POINTS_WITH_A_SD_PICTURE = 10;
  private static final Integer POINTS_WITH_DESCRIPTION = 5;

  @Test
  public void return_Zero_Value_When_Score_Has_Not_Been_Updated() {

    //Arrange
    Ad ad = AdMother.adInitial();

    //Act

    //Assert
    Assertions.assertThat(ad).isNotNull().extracting(Ad::getScore).isEqualTo(0);
  }

  @Test
  public void return_Score_Minus_Ten_Points_Given_An_Ad_Without_Pictures_When_Score_Has_Been_Updated() {

    //Arrange
    Ad adWithoutPictures = AdMother.adWithoutPictures();

    //Act
    Ad adWithoutPicturesWithScoreUpdated = adWithoutPictures.updateScore(Arrays.asList(new PictureScore()));

    //Assert
    Assertions.assertThat(adWithoutPicturesWithScoreUpdated).isNotNull()
        .extracting(Ad::getScore)
        .isEqualTo(Math.addExact(adWithoutPictures.getScore(), POINTS_WITHOUT_PICTURE));
  }

  @Test
  public void return_Score_Plus_Twenty_Points_Given_An_Ad_With_A_HD_Picture_When_Score_Has_Been_Updated() {

    //Arrange
    Ad adWithAnHDPicture = AdMother.adWithAnHDPicture();

    //Act
    Ad adWithAnHDPictureWithScoreUpdated = adWithAnHDPicture.updateScore(Arrays.asList(new PictureScore()));

    //Assert
    Assertions.assertThat(adWithAnHDPictureWithScoreUpdated).isNotNull()
        .extracting(Ad::getScore)
        .isEqualTo(Math.addExact(adWithAnHDPicture.getScore(),
            (POINTS_WITH_A_HD_PICTURE * adWithAnHDPicture.getPictures().size())));
  }

  @Test
  public void return_Score_Plus_Forty_Points_Given_An_Ad_With_Two_HD_Pictures_When_Score_Has_Been_Updated() {

    //Arrange
    Ad adWithTwoHDPictures = AdMother.adWithTwoHDPictures();

    //Act
    Ad adWithTwoHDPicturesWithScoreUpdated = adWithTwoHDPictures.updateScore(Arrays.asList(new PictureScore()));

    //Assert
    Assertions.assertThat(adWithTwoHDPicturesWithScoreUpdated).isNotNull()
        .extracting(Ad::getScore)
        .isEqualTo(Math.addExact(adWithTwoHDPictures.getScore(),
            (POINTS_WITH_A_HD_PICTURE * adWithTwoHDPictures.getPictures().size())));
  }

  @Test
  public void return_Score_Plus_Ten_Points_Given_An_Ad_With_A_SD_Picture_When_Score_Has_Been_Updated() {

    //Arrange
    Ad adWithASDPicture = AdMother.adWithASDPicture();

    //Act
    Ad adWithASDPictureWithScoreUpdated = adWithASDPicture.updateScore(Arrays.asList(new PictureScore()));

    //Assert
    Assertions.assertThat(adWithASDPictureWithScoreUpdated).isNotNull()
        .extracting(Ad::getScore)
        .isEqualTo(Math.addExact(adWithASDPicture.getScore(),
            (POINTS_WITH_A_SD_PICTURE * adWithASDPicture.getPictures().size())));
  }

  @Test
  public void return_Score_Plus_Thirty_Points_Given_An_Ad_With_A_SD_Picture_And_A_HD_Picture_When_Score_Has_Been_Updated() {

    //Arrange
    Ad adWithASDPictureAndAHDPicture = AdMother.adWithASDPictureAndAHDPicture();

    int hdPictureCount = Math.toIntExact(
        adWithASDPictureAndAHDPicture.getPictures().stream().filter(Picture::isHighDefinition)
            .count());
    int sdPictureCount = Math.toIntExact(
        adWithASDPictureAndAHDPicture.getPictures().stream().filter(Picture::isStandardDefinition)
            .count());

    //Act
    Ad adWithASDPictureAndAHDPictureWithScoreUpdated = adWithASDPictureAndAHDPicture.updateScore(Arrays.asList(new PictureScore()));

    //Assert
    Assertions.assertThat(adWithASDPictureAndAHDPictureWithScoreUpdated).isNotNull()
        .extracting(Ad::getScore)
        .isEqualTo(Math.addExact(
            Math.addExact(adWithASDPictureAndAHDPicture.getScore(),
                POINTS_WITH_A_SD_PICTURE * sdPictureCount),
            POINTS_WITH_A_HD_PICTURE * hdPictureCount));
  }

  @Test
  public void return_Score_Plus_Five_Points_Given_An_Ad_With_Description_When_Score_Has_Been_Updated() {
    //Arrange
    Ad adWithDescription = AdMother.adWithDescription();

    //Act
    Ad adWithDescriptionWithScoreUpdated = adWithDescription.updateScore(Arrays.asList(new DescriptionScore()));

    //Assert
    Assertions.assertThat(adWithDescriptionWithScoreUpdated).isNotNull()
        .extracting(Ad::getScore)
        .isEqualTo(Math.addExact(adWithDescription.getScore(), POINTS_WITH_DESCRIPTION));
  }
}
