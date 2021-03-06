package com.idealista.adrankingchallenge.infrastructure.persistence.inmemory;

import com.idealista.adrankingchallenge.domain.ad.Ad;
import com.idealista.adrankingchallenge.infrastructure.persistence.AdVO;
import com.idealista.adrankingchallenge.infrastructure.persistence.PictureVO;
import com.idealista.adrankingchallenge.infrastructure.persistence.mapper.AdToAdVOMapper;
import com.idealista.adrankingchallenge.infrastructure.persistence.mapper.PictureToPictureVOMapper;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

public class InMemoryPersistence {

  private List<AdVO> ads;
  private List<PictureVO> pictures;

  private Map<Integer, AdVO> adsWithPrimaryKey;
  private Map<Integer, PictureVO> picturesWithPrimaryKey;

  private final AdToAdVOMapper adToAdVOMapper;
  private final PictureToPictureVOMapper pictureToPictureVOMapper;

  public InMemoryPersistence() {

    this.pictureToPictureVOMapper = new PictureToPictureVOMapper();
    this.adToAdVOMapper = new AdToAdVOMapper();

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

    adsWithPrimaryKey = new HashMap<>();
    picturesWithPrimaryKey = new HashMap<>();
    ads.forEach(adVO -> adsWithPrimaryKey.put(adVO.getId(), adVO));
    pictures.forEach(pictureVO -> picturesWithPrimaryKey.put(pictureVO.getId(), pictureVO));
  }

  public Map<Integer, AdVO> getAds() {
    return Collections.unmodifiableMap(adsWithPrimaryKey);
  }

  public Map<Integer, PictureVO> getPictures() {
    return Collections.unmodifiableMap(picturesWithPrimaryKey);
  }

  public void savePicturesOnCascade(Ad ad) {
    picturesWithPrimaryKey.putAll(
        ad.getPictures()
          .stream()
          .map(pictureToPictureVOMapper)
          .collect(Collectors.toMap(PictureVO::getId, Function.identity(),
                                    (existing, replacement) -> existing)));
  }

  public void saveAd(Ad ad) {
    adsWithPrimaryKey.put(ad.getId().value(), adToAdVOMapper.apply(ad));
  }

  protected void clearAds(){
    adsWithPrimaryKey.clear();
  }

  protected void clearPictures(){
    picturesWithPrimaryKey.clear();
  }
}
