package com.idealista.adrankingchallenge.domain.ad;

import static com.idealista.adrankingchallenge.domain.ad.AdScore.MAX_SCORE;
import static com.idealista.adrankingchallenge.domain.ad.AdScore.MIN_SCORE;
import static com.idealista.adrankingchallenge.domain.ad.scoring.DescriptionScore.POINTS_FLAT_WITH_LONG_DESCRIPTION;
import static com.idealista.adrankingchallenge.domain.ad.scoring.DescriptionScore.POINTS_WITH_DESCRIPTION;
import static com.idealista.adrankingchallenge.domain.ad.scoring.PictureScore.POINTS_WITH_HD_PICTURE;
import static com.idealista.adrankingchallenge.domain.ad.scoring.PictureScore.POINTS_WITH_SD_PICTURE;

import com.idealista.adrankingchallenge.domain.ad.create.AdMother;
import com.idealista.adrankingchallenge.domain.ad.scoring.AdCompleteScore;
import com.idealista.adrankingchallenge.domain.ad.scoring.DescriptionScore;
import com.idealista.adrankingchallenge.domain.ad.scoring.PictureScore;
import java.util.Arrays;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

public class AdShould {

  @Test
  public void return_Zero_Value_When_Score_Has_Not_Been_Updated() {

    //Arrange
    Ad adEmpty = AdMother.adEmpty();

    //Act

    //Assert
    Assertions.assertThat(adEmpty)
              .isNotNull()
              .extracting(ad -> ad.getScore().value())
              .isEqualTo(0);
  }

  @Test
  public void return_Score_Minus_Ten_Points_Given_An_Ad_Without_Pictures_When_Score_Has_Been_Updated() {

    //Arrange
    Ad adWithoutPictures = AdMother.adWithoutPictures();

    //Act
    Ad adWithoutPicturesWithScoreUpdated = adWithoutPictures
        .updateScore(Arrays.asList(new PictureScore()));

    //Assert
    Assertions.assertThat(adWithoutPicturesWithScoreUpdated)
              .isNotNull()
              .extracting(ad -> ad.getScore().value())
              .isEqualTo(MIN_SCORE);
  }

  @Test
  public void return_Score_Plus_Thirty_Points_Given_An_Ad_With_A_SD_Picture_And_A_HD_Picture_When_Score_Has_Been_Updated() {

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
    Ad adWithASDPictureAndAHDPictureWithScoreUpdated = adWithASDPictureAndAHDPicture
        .updateScore(Arrays.asList(new PictureScore()));

    //Assert
    Assertions.assertThat(adWithASDPictureAndAHDPictureWithScoreUpdated)
              .isNotNull()
              .extracting(ad -> ad.getScore().value())
              .isEqualTo(Math.addExact(
                  Math.addExact(adWithASDPictureAndAHDPicture.getScore().value(),
                                POINTS_WITH_SD_PICTURE * sdPictureCount),
                  POINTS_WITH_HD_PICTURE * hdPictureCount));
  }

  @Test
  public void return_Score_Plus_Five_Points_Given_An_Ad_With_Description_When_Score_Has_Been_Updated() {
    //Arrange
    Ad adFlatWithShortDescription = AdMother.adFlatWithNineteenWordsInTheDescription();

    //Act
    Ad adFlatWithShortDescriptionWithScoreUpdated = adFlatWithShortDescription
        .updateScore(Arrays.asList(new DescriptionScore()));

    //Assert
    Assertions.assertThat(adFlatWithShortDescriptionWithScoreUpdated)
              .isNotNull()
              .extracting(ad -> ad.getScore().value())
              .isEqualTo(
                  Math.addExact(adFlatWithShortDescription.getScore().value(), POINTS_WITH_DESCRIPTION));
  }

  @Test
  public void return_Score_Plus_FiftyFive_Points_Given_A_Flat_Ad_With_HD_Picture_And_Long_Description_When_Score_Has_Been_Updated() {
    //Arrange
    Ad adFlatHDWithLongDescription = AdMother.adFlatWithHDPictureAndFiftyWordsInTheDescription();

    //Act
    Ad adFlatHDWithLongDescriptionWithScoreUpdated = adFlatHDWithLongDescription
        .updateScore(Arrays.asList(new PictureScore(), new DescriptionScore()));

    //Assert
    Assertions.assertThat(adFlatHDWithLongDescriptionWithScoreUpdated)
              .isNotNull()
              .extracting(ad -> ad.getScore().value())
              .isEqualTo(Math.addExact(adFlatHDWithLongDescription.getScore().value(),
                                       POINTS_WITH_HD_PICTURE + POINTS_WITH_DESCRIPTION
                                           + POINTS_FLAT_WITH_LONG_DESCRIPTION));
  }

  @Test
  public void return_Maximun_Score_Points_Given_A_Chalet_Ad_Complete_With_Three_HD_Pictures_When_Score_Has_Been_Updated() {
    //Arrange
    Ad adChaletCompleteWithThreeHDPictures = AdMother.adChaletCompleteWithThreeHDPictures();

    //Act
    Ad adChaletWithScoreExceed = adChaletCompleteWithThreeHDPictures
        .updateScore(
            Arrays.asList(new PictureScore(), new DescriptionScore(), new AdCompleteScore()));

    //Assert
    Assertions.assertThat(adChaletWithScoreExceed)
              .isNotNull()
              .extracting(ad -> ad.getScore().value())
              .isEqualTo(MAX_SCORE);
  }

  @Test
  public void return_Minimun_Score_Points_Given_A_Garage_Empty_When_Score_Has_Been_Updated() {
    //Arrange
    Ad adWithoutInformation = AdMother.adEmpty();

    //Act
    Ad adFlatWithLowestScore = adWithoutInformation
        .updateScore(
            Arrays.asList(new PictureScore(), new DescriptionScore(), new AdCompleteScore()));

    //Assert
    Assertions.assertThat(adFlatWithLowestScore)
              .isNotNull()
              .extracting(ad -> ad.getScore().value())
              .isEqualTo(MIN_SCORE);
  }
}
