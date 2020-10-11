package com.idealista.adrankingchallenge.acceptance.config;

import com.idealista.adrankingchallenge.domain.ad.Ad;
import com.idealista.adrankingchallenge.domain.ad.AdRepository;
import com.idealista.adrankingchallenge.domain.ad.search.AdsFound;
import com.idealista.adrankingchallenge.infrastructure.persistence.AdVO;
import com.idealista.adrankingchallenge.infrastructure.persistence.PictureVO;
import com.idealista.adrankingchallenge.infrastructure.persistence.mapper.AdToAdVOMapper;
import com.idealista.adrankingchallenge.infrastructure.persistence.mapper.AdVOToAdMapper;
import com.idealista.adrankingchallenge.infrastructure.persistence.mapper.PictureToPictureVOMapper;
import com.idealista.adrankingchallenge.infrastructure.persistence.mapper.PictureVOToPictureMapper;
import java.util.Comparator;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.function.Function;
import java.util.stream.Collectors;

public class AdRepositoryStub implements AdRepository {

  private Map<Integer, AdVO> adsWithPrimaryKey;
  private Map<Integer, PictureVO> picturesWithPrimaryKey;

  private final AdVOToAdMapper adVOToAdMapper;
  private final AdToAdVOMapper adToAdVOMapper;

  private final PictureVOToPictureMapper pictureVOToPictureMapper;
  private final PictureToPictureVOMapper pictureToPictureVOMapper;

  public AdRepositoryStub(
      AdVOToAdMapper adVOToAdMapper, AdToAdVOMapper adToAdVOMapper,
      PictureVOToPictureMapper pictureVOToPictureMapper,
      PictureToPictureVOMapper pictureToPictureVOMapper) {

    this.adVOToAdMapper = adVOToAdMapper;
    this.adToAdVOMapper = adToAdVOMapper;
    this.pictureVOToPictureMapper = pictureVOToPictureMapper;
    this.pictureToPictureVOMapper = pictureToPictureVOMapper;

    adsWithPrimaryKey = new HashMap<>();
    picturesWithPrimaryKey = new HashMap<>();
  }

  @Override
  public AdsFound findAllOrderByScore() {

    Map<Integer, AdVO> adsWithPrimaryKeyOrdered = adsWithPrimaryKey.entrySet().stream()
        .sorted(Comparator.nullsLast(Entry.comparingByValue(
            Comparator.comparingInt(AdVO::getScore).reversed())))
        .collect(
            Collectors.toMap(Entry::getKey, Entry::getValue, (ad1, ad2) -> ad1, LinkedHashMap::new));

    return new AdsFound(
        adsWithPrimaryKeyOrdered.values().stream().map(adVO -> {
          List<PictureVO> pictures = adVO.getPictures().stream()
              .map(pictureKey -> picturesWithPrimaryKey.get(pictureKey)).collect(
                  Collectors.toList());
          return adVOToAdMapper.convert(adVO, pictures);
        })
            .collect(Collectors.toList()));
  }

  @Override
  public void save(Ad ad) {
    picturesWithPrimaryKey.putAll(
        ad.getPictures().stream().map(pictureToPictureVOMapper)
            .collect(Collectors.toMap(PictureVO::getId, Function.identity(),
                (existing, replacement) -> existing)));
    adsWithPrimaryKey.put(ad.getId(), adToAdVOMapper.apply(ad));
  }

  @Override
  public Ad findById(Integer identifier) {
    AdVO adVOFound = adsWithPrimaryKey.get(identifier);
    List<PictureVO> pictures = adVOFound.getPictures().stream()
        .map(pictureKey -> picturesWithPrimaryKey.get(pictureKey)).collect(
            Collectors.toList());
    return adVOToAdMapper.convert(adVOFound, pictures);
  }
}
