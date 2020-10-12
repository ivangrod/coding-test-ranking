package com.idealista.adrankingchallenge.acceptance;

import com.idealista.adrankingchallenge.acceptance.config.AcceptanceConfig;
import com.idealista.adrankingchallenge.acceptance.config.InMemoryPersistenceStub;
import com.idealista.adrankingchallenge.application.ad.scoring.AdsScoreCalculator;
import com.idealista.adrankingchallenge.application.ad.search.AdFound;
import com.idealista.adrankingchallenge.application.ad.search.AdIrrelevantSearcher;
import com.idealista.adrankingchallenge.application.ad.search.AdPublicSearcher;
import com.idealista.adrankingchallenge.application.ad.search.SearchingAdReturn;
import com.idealista.adrankingchallenge.domain.ad.Ad;
import com.idealista.adrankingchallenge.domain.ad.AdRepository;
import com.idealista.adrankingchallenge.domain.ad.Picture;
import com.idealista.adrankingchallenge.domain.ad.Picture.Definition;
import com.idealista.adrankingchallenge.domain.ad.Typology;
import java.time.Instant;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import org.apache.commons.lang3.StringUtils;
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
    Ad adWithoutPictures = Ad.createAd(1, Typology.FLAT, StringUtils.EMPTY, Arrays
                                           .asList(new Picture(1, "http://www.idealista.com/pictures/1", Definition.SD)), 0,
                                       0);
    List<Picture> twoHDPictures = Arrays
        .asList(new Picture(2, "http://www.idealista.com/pictures/2", Definition.HD),
                new Picture(3, "http://www.idealista.com/pictures/3", Definition.HD));
    Ad adWithSDAndHDPicture = Ad.createAd(2, Typology.FLAT, "Beautiful house", twoHDPictures, 0, 0);

    adRepository.save(adWithoutPictures);
    adRepository.save(adWithSDAndHDPicture);

    adsScoreCalculator.execute();

    Ad adWithoutPicturesScoreUpdate = adRepository.findById(adWithoutPictures.getId());
    Ad adWithPicturesScoreUpdate = adRepository.findById(adWithSDAndHDPicture.getId());

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
    Ad adWithoutPictures = Ad.createAd(1, Typology.FLAT, StringUtils.EMPTY, Arrays
                                           .asList(new Picture(1, "http://www.idealista.com/pictures/1", Definition.SD)), 0,
                                       0);
    List<Picture> twoHDPictures = Arrays
        .asList(new Picture(2, "http://www.idealista.com/pictures/2", Definition.HD),
                new Picture(3, "http://www.idealista.com/pictures/3", Definition.HD));
    Ad adWithSDAndHDPicture = Ad.createAd(2, Typology.FLAT, "Beautiful house", twoHDPictures, 0, 0);

    adRepository.save(adWithoutPictures);
    adRepository.save(adWithSDAndHDPicture);

    adsScoreCalculator.execute();

    Ad adWithoutPicturesScoreUpdate = adRepository.findById(adWithoutPictures.getId());
    Ad adWithPicturesScoreUpdate = adRepository.findById(adWithSDAndHDPicture.getId());

    // When
    SearchingAdReturn adsPublicFound = adIrrelevantSearcher.execute();

    // Then
    Assertions.assertThat(adsPublicFound.getAds())
              .isNotEmpty()
              .containsExactly(AdFound.fromAd(adWithoutPicturesScoreUpdate))
              .allMatch(
                  adFound ->
                      adFound.getScore() < 40 && adFound.getIrrelevantSince()
                                                        .before(Date.from(
                                                            Instant.now())))
              .doesNotContain(AdFound.fromAd(adWithPicturesScoreUpdate));
  }
}
