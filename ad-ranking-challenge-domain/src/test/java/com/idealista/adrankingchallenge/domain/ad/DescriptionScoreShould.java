package com.idealista.adrankingchallenge.domain.ad;

import static com.idealista.adrankingchallenge.domain.ad.scoring.DescriptionScore.POINTS_CHALET_WITH_LONG_DESCRIPTION;
import static com.idealista.adrankingchallenge.domain.ad.scoring.DescriptionScore.POINTS_FLAT_WITH_LONG_DESCRIPTION;
import static com.idealista.adrankingchallenge.domain.ad.scoring.DescriptionScore.POINTS_FLAT_WITH_MEDIUM_DESCRIPTION;
import static com.idealista.adrankingchallenge.domain.ad.scoring.DescriptionScore.POINTS_WITH_DESCRIPTION;
import static com.idealista.adrankingchallenge.domain.ad.scoring.DescriptionScore.POINTS_WITH_KEYWORD_DESCRIPTION;

import com.idealista.adrankingchallenge.domain.ad.create.AdMother;
import com.idealista.adrankingchallenge.domain.ad.scoring.DescriptionScore;
import java.util.Arrays;
import java.util.List;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

public class DescriptionScoreShould {

  @Test
  public void return_Zero_Points_Given_An_Ad_Without_Description() {

    //Arrange
    Ad adWithoutDescription = AdMother.adWithoutDescription();

    //Act
    int zeroPoint = new DescriptionScore().pointsToAdd(adWithoutDescription);

    //Assert
    Assertions.assertThat(zeroPoint)
              .isEqualTo(0);
  }

  @Test
  public void return_Five_Points_Given_An_Ad_With_Description() {

    //Arrange
    Ad adWithDescription = AdMother.adWithDescription();

    //Act
    int fivePoints = new DescriptionScore().pointsToAdd(adWithDescription);

    //Assert
    Assertions.assertThat(fivePoints)
              .isEqualTo(POINTS_WITH_DESCRIPTION);
  }

  @Test
  public void return_Five_Points_Given_A_Flat_Ad_With_Short_Description() {

    //Arrange
    Ad adFlatWithShortDescription = AdMother.adFlatWithNineteenWordsInTheDescription();

    //Act
    int fivePoints = new DescriptionScore().pointsToAdd(adFlatWithShortDescription);

    //Assert
    Assertions.assertThat(fivePoints)
              .isEqualTo(POINTS_WITH_DESCRIPTION);
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

  @Test
  public void return_Ten_Points_Given_An_Ad_With_A_Keyword_In_Its_Description() {

    //Arrange
    List<String> keywordInDescription = Arrays.asList("Luminoso");
    Ad luminousAd = AdMother.adGarageWithDescriptionContainingTheWords(keywordInDescription);

    //Act
    int tenPoints = new DescriptionScore().pointsToAdd(luminousAd);

    //Assert
    Assertions.assertThat(tenPoints)
              .isEqualTo(
                  POINTS_WITH_DESCRIPTION + (POINTS_WITH_KEYWORD_DESCRIPTION * keywordInDescription.size()));
  }

  @Test
  public void return_Twenty_Points_Given_An_Ad_With_Three_Keywords_In_Its_Description() {

    //Arrange
    List<String> keywordInDescription = Arrays.asList("Luminoso", "Céntrico", "Reformado");
    Ad luminousCentricRenovatedAd = AdMother
        .adGarageWithDescriptionContainingTheWords(keywordInDescription);

    //Act
    int twentyPoints = new DescriptionScore().pointsToAdd(luminousCentricRenovatedAd);

    //Assert
    Assertions.assertThat(twentyPoints)
              .isEqualTo(
                  POINTS_WITH_DESCRIPTION + (POINTS_WITH_KEYWORD_DESCRIPTION * keywordInDescription.size()));
  }

  @Test
  public void return_Thirty_Points_Given_An_Ad_With_All_Keywords_In_Its_Description() {

    //Arrange
    List<String> keywordInDescription = Arrays
        .asList("Luminoso", "Nuevo", "Céntrico", "Reformado", "Ático");
    Ad luminousNewCentricRenovatedAtticAd = AdMother
        .adGarageWithDescriptionContainingTheWords(keywordInDescription);

    //Act
    int thirtyPoints = new DescriptionScore().pointsToAdd(luminousNewCentricRenovatedAtticAd);

    //Assert
    Assertions.assertThat(thirtyPoints)
              .isEqualTo(
                  POINTS_WITH_DESCRIPTION + (POINTS_WITH_KEYWORD_DESCRIPTION * keywordInDescription.size()));
  }

  @Test
  public void return_TwentyFive_Points_Given_An_Ad_With_Four_Misspelled_Keywords_In_Its_Description() {

    //Arrange
    List<String> misspelledKeywordInDescription = Arrays
        .asList("luminÓso", "nueVo", "Ceńtrico", "atIco");
    Ad luminousNewCentricAtticAd = AdMother
        .adGarageWithDescriptionContainingTheWords(misspelledKeywordInDescription);

    //Act
    int twentyFivePoints = new DescriptionScore().pointsToAdd(luminousNewCentricAtticAd);

    //Assert
    Assertions.assertThat(twentyFivePoints)
              .isEqualTo(POINTS_WITH_DESCRIPTION + (POINTS_WITH_KEYWORD_DESCRIPTION
                  * misspelledKeywordInDescription.size()));
  }
}
