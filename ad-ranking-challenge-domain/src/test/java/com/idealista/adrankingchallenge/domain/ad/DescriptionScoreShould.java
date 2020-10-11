package com.idealista.adrankingchallenge.domain.ad;

import com.idealista.adrankingchallenge.domain.ad.scoring.DescriptionScore;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

public class DescriptionScoreShould {

  private static final Integer POINTS_WITH_DESCRIPTION = 5;
  private static final Integer POINTS_FLAT_WITH_MEDIUM_DESCRIPTION = 10;
  private static final Integer POINTS_FLAT_WITH_LONG_DESCRIPTION = 30;
  private static final Integer POINTS_CHALET_WITH_LONG_DESCRIPTION = 20;

  @Test
  public void return_Zero_Points_Given_An_Ad_Without_Description() {

    //Arrange
    Ad adWithoutDescription = AdMother.adWithoutDescription();

    //Act
    int zeroPoint = new DescriptionScore().pointsToAdd(adWithoutDescription);

    //Assert
    Assertions.assertThat(zeroPoint).isEqualTo(0);
  }

  @Test
  public void return_Five_Points_Given_An_Ad_With_Description() {

    //Arrange
    Ad adWithDescription = AdMother.adWithDescription();

    //Act
    int fivePoints = new DescriptionScore().pointsToAdd(adWithDescription);

    //Assert
    Assertions.assertThat(fivePoints).isEqualTo(POINTS_WITH_DESCRIPTION);
  }

  @Test
  public void return_Five_Points_Given_A_Flat_Ad_With_Short_Description() {

    //Arrange
    Ad adFlatWithShortDescription = AdMother.adFlatWithNineteenWordsInTheDescription();

    //Act
    int fivePoints = new DescriptionScore().pointsToAdd(adFlatWithShortDescription);

    //Assert
    Assertions.assertThat(fivePoints).isEqualTo(POINTS_WITH_DESCRIPTION);
  }

  @Test
  public void return_Fifteen_Points_Given_A_Flat_Ad_With_Medium_Description() {

    //Arrange
    Ad adFlatWithMediumDescription = AdMother.adFlatWithThirtyWordsInTheDescription();

    //Act
    int fifteenPoints = new DescriptionScore().pointsToAdd(adFlatWithMediumDescription);

    //Assert
    Assertions.assertThat(fifteenPoints)
        .isEqualTo(POINTS_WITH_DESCRIPTION + POINTS_FLAT_WITH_MEDIUM_DESCRIPTION);
  }

  @Test
  public void return_ThirtyFive_Points_Given_A_Flat_Ad_With_Long_Description() {

    //Arrange
    Ad adFlatWithLongDescription = AdMother.adFlatWithFiftyWordsInTheDescription();

    //Act
    int thirtyFivePoints = new DescriptionScore().pointsToAdd(adFlatWithLongDescription);

    //Assert
    Assertions.assertThat(thirtyFivePoints)
        .isEqualTo(POINTS_WITH_DESCRIPTION + POINTS_FLAT_WITH_LONG_DESCRIPTION);
  }

  @Test
  public void return_TwentyFive_Points_Given_A_Chalet_Ad_With_Long_Description() {

    //Arrange
    Ad adChaletWithLongDescription = AdMother.adChaletWithFiftyWordsInTheDescription();

    //Act
    int twentyFivePoints = new DescriptionScore().pointsToAdd(adChaletWithLongDescription);

    //Assert
    Assertions.assertThat(twentyFivePoints)
        .isEqualTo(POINTS_WITH_DESCRIPTION + POINTS_CHALET_WITH_LONG_DESCRIPTION);
  }
}
