package com.idealista.adrankingchallenge.domain.ad;

import com.idealista.adrankingchallenge.domain.ad.scoring.DescriptionScore;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

public class DescriptionScoreShould {

  private static final Integer POINTS_WITH_DESCRIPTION = 5;
  private static final Integer POINTS_WITH_MEDIUM_DESCRIPTION = 10;

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
  public void return_Fifteen_Points_Given_An_Flat_Ad_With_Medium_Description() {

    //Arrange
    Ad adFlatWithMediumDescription = AdMother.adFlatWithThirtyWordsInTheDescription();

    //Act
    int fifteenPoints = new DescriptionScore().pointsToAdd(adFlatWithMediumDescription);

    //Assert
    Assertions.assertThat(fifteenPoints)
        .isEqualTo(POINTS_WITH_DESCRIPTION + POINTS_WITH_MEDIUM_DESCRIPTION);
  }
}
