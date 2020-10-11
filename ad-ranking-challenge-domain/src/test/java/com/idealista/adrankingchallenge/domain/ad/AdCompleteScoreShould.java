package com.idealista.adrankingchallenge.domain.ad;

import com.idealista.adrankingchallenge.domain.ad.scoring.AdCompleteScore;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

public class AdCompleteScoreShould {

  private static final Integer POINTS_COMPLETE_AD = 40;

  @Test
  public void return_Forty_Points_Given_A_Flat_Complete_Ad() {

    //Arrange
    Ad adFlatComplete = AdMother.adFlatComplete();

    //Act
    int fortyPoints = new AdCompleteScore().pointsToAdd(adFlatComplete);

    //Assert
    Assertions.assertThat(fortyPoints)
              .isEqualTo(POINTS_COMPLETE_AD);
  }

  @Test
  public void return_Forty_Points_Given_A_Chalet_Complete_Ad() {

    //Arrange
    Ad adChaletComplete = AdMother.adChaletComplete();

    //Act
    int fortyPoints = new AdCompleteScore().pointsToAdd(adChaletComplete);

    //Assert
    Assertions.assertThat(fortyPoints)
              .isEqualTo(POINTS_COMPLETE_AD);
  }

  @Test
  public void return_Forty_Points_Given_A_Garage_With_Description_Complete_Ad() {

    //Arrange
    Ad adGarageWithDescriptionComplete = AdMother.adGarageWithDescriptionComplete();

    //Act
    int fortyPoints = new AdCompleteScore().pointsToAdd(adGarageWithDescriptionComplete);

    //Assert
    Assertions.assertThat(fortyPoints)
              .isEqualTo(POINTS_COMPLETE_AD);
  }

  @Test
  public void return_Forty_Points_Given_A_Garage_Without_Description_Complete_Ad() {

    //Arrange
    Ad adGarageWithoutDescriptionComplete = AdMother.adGarageWithoutDescriptionComplete();

    //Act
    int fortyPoints = new AdCompleteScore().pointsToAdd(adGarageWithoutDescriptionComplete);

    //Assert
    Assertions.assertThat(fortyPoints)
              .isEqualTo(POINTS_COMPLETE_AD);
  }
}
