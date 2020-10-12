package com.idealista.adrankingchallenge.acceptance;

import com.idealista.adrankingchallenge.acceptance.config.AcceptanceConfig;
import com.idealista.adrankingchallenge.acceptance.config.InMemoryPersistenceStub;
import com.idealista.adrankingchallenge.application.ad.scoring.AdsScoreCalculator;
import com.idealista.adrankingchallenge.application.ad.search.AdFound;
import com.idealista.adrankingchallenge.application.ad.search.AdIrrelevantSearcher;
import com.idealista.adrankingchallenge.application.ad.search.AdPublicSearcher;
import com.idealista.adrankingchallenge.application.ad.search.SearchingAdReturn;
import com.idealista.adrankingchallenge.create.AdMother;
import com.idealista.adrankingchallenge.domain.ad.Ad;
import com.idealista.adrankingchallenge.domain.ad.AdRepository;
import java.time.Instant;
import java.util.Date;
import java.util.function.Predicate;
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
public class AdsSearcherTest {

  @Autowired
  private AdRepository adRepository;

  @Autowired
  private InMemoryPersistenceStub inMemoryPersistenceStub;

  @Autowired
  private AdPublicSearcher adPublicSearcher;

  @Autowired
  private AdsScoreCalculator adsScoreCalculator;

  @Autowired
  private AdIrrelevantSearcher adIrrelevantSearcher;

  @Before
  public void setUp() {
    inMemoryPersistenceStub.clear();
  }

  @Test
  public void should_Return_Only_Relevant_Ads_When_Store_Calculator_Is_Invoked() {

    // Given
    Ad adWithoutPictures = AdMother.adFlatWithouDescriptionWithAnSDPictureAndIdentifierFrom(1);
    Ad adWithSDAndHDPicture = AdMother.adWithTwoHDPicturesAndIdentifierFrom(2);

    adRepository.save(adWithoutPictures);
    adRepository.save(adWithSDAndHDPicture);

    adsScoreCalculator.execute();

    Ad adWithoutPicturesScoreUpdate = adRepository.findById(adWithoutPictures.getId().value());
    Ad adWithPicturesScoreUpdate = adRepository.findById(adWithSDAndHDPicture.getId().value());

    // When
    SearchingAdReturn adsPublicFound = adPublicSearcher.execute();

    // Then
    Assertions.assertThat(adsPublicFound.getAds())
              .isNotEmpty()
              .containsExactly(AdFound.fromAd(adWithPicturesScoreUpdate))
              .allMatch(adFound -> adFound.getScore() >= 40)
              .doesNotContain(AdFound.fromAd(adWithoutPicturesScoreUpdate));
  }

  @Test
  public void should_Return_Only_Irrelevant_Ads_When_Store_Calculator_Is_Invoked() {

    // Given
    Ad adWithoutPictures = AdMother.adFlatWithouDescriptionWithAnSDPictureAndIdentifierFrom(1);
    Ad adWithSDAndHDPicture = AdMother.adWithTwoHDPicturesAndIdentifierFrom(2);

    adRepository.save(adWithoutPictures);
    adRepository.save(adWithSDAndHDPicture);

    adsScoreCalculator.execute();

    Ad adWithoutPicturesScoreUpdate = adRepository.findById(adWithoutPictures.getId().value());
    Ad adWithPicturesScoreUpdate = adRepository.findById(adWithSDAndHDPicture.getId().value());

    // When
    SearchingAdReturn adsPublicFound = adIrrelevantSearcher.execute();

    // Then
    Assertions.assertThat(adsPublicFound.getAds())
              .isNotEmpty()
              .containsExactly(AdFound.fromAd(adWithoutPicturesScoreUpdate))
              .allMatch(isIrrelevantBeforeNow())
              .doesNotContain(AdFound.fromAd(adWithPicturesScoreUpdate));
  }

  private Predicate<? super AdFound> isIrrelevantBeforeNow() {
    return adFound -> adFound.getScore() < 40 && adFound.getIrrelevantSince().before(Date.from(Instant.now()));
  }
}
