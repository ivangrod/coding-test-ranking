package com.idealista.adrankingchallenge.infrastructure.persistence;

import com.idealista.adrankingchallenge.domain.ad.Ad;
import com.idealista.adrankingchallenge.domain.ad.AdRepository;
import com.idealista.adrankingchallenge.domain.ad.search.AdsFound;
import com.idealista.adrankingchallenge.infrastructure.persistence.inmemory.InMemoryPersistence;
import com.idealista.adrankingchallenge.infrastructure.persistence.mapper.AdToAdVOMapper;
import com.idealista.adrankingchallenge.infrastructure.persistence.mapper.AdVOToAdMapper;
import com.idealista.adrankingchallenge.infrastructure.persistence.mapper.PictureToPictureVOMapper;
import com.idealista.adrankingchallenge.infrastructure.persistence.mapper.PictureVOToPictureMapper;
import java.util.Comparator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.stream.Collectors;
import org.springframework.stereotype.Component;

@Component
public class InMemoryAdRepository implements AdRepository {

  private final AdVOToAdMapper adVOToAdMapper;

  private InMemoryPersistence inMemoryPersistence;

  public InMemoryAdRepository(InMemoryPersistence inMemoryPersistence) {

    this.adVOToAdMapper = new AdVOToAdMapper(new PictureVOToPictureMapper());
    this.inMemoryPersistence = inMemoryPersistence;
  }

  @Override
  public AdsFound findAllOrderByScore() {

    Map<Integer, AdVO> adsWithPrimaryKeyOrdered =
        inMemoryPersistence.getAds()
                           .entrySet()
                           .stream()
                           .sorted(compareByScoreDesc())
                           .collect(Collectors.toMap(Entry::getKey, Entry::getValue, (ad1, ad2) -> ad1,
                                                     LinkedHashMap::new));

    return new AdsFound(adsWithPrimaryKeyOrdered.values()
                                                .stream()
                                                .map(adVO -> adVOToAdMapper.convert(adVO, getPicturesVO(adVO)))
                                                .collect(Collectors.toList()));
  }

  @Override
  public void save(Ad ad) {
    inMemoryPersistence.savePicturesOnCascade(ad);
    inMemoryPersistence.saveAd(ad);
  }

  @Override
  public Ad findById(Integer identifier) {
    AdVO adVOFound = inMemoryPersistence.getAds().get(identifier);
    return adVOToAdMapper.convert(adVOFound, getPicturesVO(adVOFound));
  }

  private Comparator<? super Entry<Integer, AdVO>> compareByScoreDesc() {
    return Comparator.nullsLast(Entry.comparingByValue(Comparator.comparingInt(AdVO::getScore).reversed()));
  }

  private List<PictureVO> getPicturesVO(AdVO adVO) {
    return adVO.getPictures()
               .stream()
               .map(pictureKey -> inMemoryPersistence.getPictures().get(pictureKey))
               .collect(Collectors.toList());
  }
}
