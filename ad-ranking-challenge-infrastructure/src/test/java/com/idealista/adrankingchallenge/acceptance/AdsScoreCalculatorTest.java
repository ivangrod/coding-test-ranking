package com.idealista.adrankingchallenge.acceptance;

import com.idealista.adrankingchallenge.acceptance.config.AcceptanceConfig;
import com.idealista.adrankingchallenge.acceptance.config.InMemoryPersistenceStub;
import com.idealista.adrankingchallenge.application.ad.scoring.AdsScoreCalculator;
import com.idealista.adrankingchallenge.create.AdMother;
import com.idealista.adrankingchallenge.domain.ad.Ad;
import com.idealista.adrankingchallenge.domain.ad.AdRepository;
import com.idealista.adrankingchallenge.domain.ad.search.AdsFound;
import org.assertj.core.api.Assertions;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = AcceptanceConfig.class)
@ActiveProfiles({"test", "test-repository"})
public class AdsScoreCalculatorTest {

  @Autowired
  private AdRepository adRepository;

  @Autowired
  private InMemoryPersistenceStub inMemoryPersistenceStub;

  @Autowired
  private AdsScoreCalculator adsScoreCalculator;

  @Before
  public void setUp() {
    inMemoryPersistenceStub.clear();
  }

  @Test
  public void should_Return_Ordered_By_Scoring_Ads_When_Store_Calculator_Is_Invoked() {

    // Given
    Ad adWithoutPictures = AdMother.adWithoutDescriptionAndIdentifierFrom(1);
    Ad adWithSDAndHDPicture = AdMother.adWithTwoHDPicturesAndIdentifierFrom(2);

    adRepository.save(adWithoutPictures);
    adRepository.save(adWithSDAndHDPicture);

    adsScoreCalculator.execute();

    Ad adWithoutPicturesScoreUpdate = adRepository.findById(adWithoutPictures.getId().value());
    Ad adWithPicturesScoreUpdate = adRepository.findById(adWithSDAndHDPicture.getId().value());

    // When
    AdsFound adsFound = adRepository.findAllOrderByScore();

    // Then
    Assertions.assertThat(adsFound.getAds())
              .isNotEmpty()
              .containsSequence(adWithPicturesScoreUpdate, adWithoutPicturesScoreUpdate);
  }
}
