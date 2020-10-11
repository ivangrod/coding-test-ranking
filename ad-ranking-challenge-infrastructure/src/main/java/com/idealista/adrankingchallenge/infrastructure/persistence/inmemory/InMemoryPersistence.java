package com.idealista.adrankingchallenge.infrastructure.persistence.inmemory;

import com.idealista.adrankingchallenge.domain.ad.Ad;
import com.idealista.adrankingchallenge.domain.ad.AdRepository;
import com.idealista.adrankingchallenge.domain.ad.search.AdsFound;
import com.idealista.adrankingchallenge.infrastructure.persistence.AdVO;
import com.idealista.adrankingchallenge.infrastructure.persistence.PictureVO;
import com.idealista.adrankingchallenge.infrastructure.persistence.mapper.AdToAdVOMapper;
import com.idealista.adrankingchallenge.infrastructure.persistence.mapper.AdVOToAdMapper;
import com.idealista.adrankingchallenge.infrastructure.persistence.mapper.PictureToPictureVOMapper;
import com.idealista.adrankingchallenge.infrastructure.persistence.mapper.PictureVOToPictureMapper;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;
import org.springframework.stereotype.Repository;

@Repository
public class InMemoryPersistence implements AdRepository {

  private List<AdVO> ads;
  private Map<Integer, AdVO> adsWithPrimaryKey;
  private List<PictureVO> pictures;
  private Map<Integer, PictureVO> picturesWithPrimaryKey;

  private final AdVOToAdMapper adVOToAdMapper;
  private final AdToAdVOMapper adToAdVOMapper;

  private final PictureVOToPictureMapper pictureVOToPictureMapper;
  private final PictureToPictureVOMapper pictureToPictureVOMapper;

  public InMemoryPersistence(AdVOToAdMapper adVOToAdMapper, AdToAdVOMapper adToAdVOMapper,
      PictureVOToPictureMapper pictureVOToPictureMapper,
      PictureToPictureVOMapper pictureToPictureVOMapper) {

    this.adVOToAdMapper = adVOToAdMapper;
    this.adToAdVOMapper = adToAdVOMapper;
    this.pictureVOToPictureMapper = pictureVOToPictureMapper;
    this.pictureToPictureVOMapper = pictureToPictureVOMapper;

    ads = new ArrayList<AdVO>();
    ads.add(new AdVO(1, "CHALET", "Este piso es una ganga, compra, compra, COMPRA!!!!!",
        Collections.<Integer>emptyList(), 300, null, null, null));
    ads.add(new AdVO(2, "FLAT",
        "Nuevo ático céntrico recién reformado. No deje pasar la oportunidad y adquiera este ático de lujo",
        Arrays
            .asList(4), 300, null, null, null));
    ads.add(new AdVO(3, "CHALET", "", Arrays.asList(2), 300, null, null, null));
    ads.add(new AdVO(4, "FLAT", "Ático céntrico muy luminoso y recién reformado, parece nuevo",
        Arrays.asList(5), 300, null, null, null));
    ads.add(new AdVO(5, "FLAT", "Pisazo,", Arrays.asList(3, 8), 300, null, null, null));
    ads.add(new AdVO(6, "GARAGE", "", Arrays.asList(6), 300, null, null, null));
    ads.add(
        new AdVO(7, "GARAGE", "Garaje en el centro de Albacete", Collections.<Integer>emptyList(),
            300, null, null, null));
    ads.add(new AdVO(8, "CHALET",
        "Maravilloso chalet situado en lAs afueras de un pequeño pueblo rural. El entorno es espectacular, las vistas magníficas. ¡Cómprelo ahora!",
        Arrays.asList(1, 7), 300, null, null, null));
    ads.add(new AdVO(9, "FLAT", "La casa es una maravilla, sinceramente", Arrays.asList(1, 7), 300,
        null, null, null));
    ads.add(new AdVO(10, "CHALET", "Poca broma el piso", Arrays.asList(2), 300, null, null, null));

    pictures = new ArrayList<PictureVO>();
    pictures.add(new PictureVO(1, "http://www.idealista.com/pictures/1", "SD"));
    pictures.add(new PictureVO(2, "http://www.idealista.com/pictures/2", "HD"));
    pictures.add(new PictureVO(3, "http://www.idealista.com/pictures/3", "SD"));
    pictures.add(new PictureVO(4, "http://www.idealista.com/pictures/4", "HD"));
    pictures.add(new PictureVO(5, "http://www.idealista.com/pictures/5", "SD"));
    pictures.add(new PictureVO(6, "http://www.idealista.com/pictures/6", "SD"));
    pictures.add(new PictureVO(7, "http://www.idealista.com/pictures/7", "SD"));
    pictures.add(new PictureVO(8, "http://www.idealista.com/pictures/8", "HD"));
    pictures.add(new PictureVO(9, "http://www.idealista.com/pictures/9", "HD"));
    pictures.add(new PictureVO(10, "http://www.idealista.com/pictures/10", "SD"));

    ads.stream().map(adVO -> adsWithPrimaryKey.put(adVO.getId(), adVO));
    pictures.stream().map(pictureVO -> picturesWithPrimaryKey.put(pictureVO.getId(), pictureVO));
  }

  @Override
  public AdsFound findAllOrderByScore() {
    // TODO Make compare -> sorted(Comparator.nullsLast(Comparator.comparing(AdVO::getScore)))
    return new AdsFound(
        ads.stream().map(adVO -> adVOToAdMapper.convert(adVO, Collections.emptyList())).collect(Collectors.toList()));
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
    return null;
  }
}
