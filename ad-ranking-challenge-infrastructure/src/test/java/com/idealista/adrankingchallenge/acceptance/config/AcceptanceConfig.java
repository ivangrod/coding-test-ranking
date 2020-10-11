package com.idealista.adrankingchallenge.acceptance.config;

import com.idealista.adrankingchallenge.domain.ad.AdRepository;
import com.idealista.adrankingchallenge.infrastructure.persistence.mapper.AdToAdVOMapper;
import com.idealista.adrankingchallenge.infrastructure.persistence.mapper.AdVOToAdMapper;
import com.idealista.adrankingchallenge.infrastructure.persistence.mapper.PictureToPictureVOMapper;
import com.idealista.adrankingchallenge.infrastructure.persistence.mapper.PictureVOToPictureMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.context.annotation.Profile;

@Configuration
@ComponentScan({"com.idealista.adrankingchallenge.infrastructure.di",
    "com.idealista.adrankingchallenge.infrastructure.persistence.mapper"})
@Profile("test-repository")
public class AcceptanceConfig {

  @Bean
  @Primary
  public AdRepository adRepositoryStub(AdVOToAdMapper adVOToAdMapper,
      AdToAdVOMapper adToAdVOMapper, PictureVOToPictureMapper pictureVOToPictureMapper,
      PictureToPictureVOMapper pictureToPictureVOMapper) {
    return new AdRepositoryStub(adVOToAdMapper, adToAdVOMapper, pictureVOToPictureMapper,
        pictureToPictureVOMapper);
  }
}
