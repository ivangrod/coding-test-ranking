package com.idealista.adrankingchallenge.infrastructure.api;

import com.idealista.adrankingchallenge.application.ad.search.AdSearcher;
import com.idealista.adrankingchallenge.application.ad.search.SearchingAdReturn;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AdsController {

  @Autowired
  private AdSearcher adSearcher;

  @GetMapping(path = "/ads/quality", produces = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity<List<QualityAd>> qualityListing() {
    SearchingAdReturn adsQualityResult = adSearcher.execute();
    return ResponseEntity
        .ok(adsQualityResult.getAds()
                           .stream()
                           .map(QualityAd::buildQualityAdFromAdFound)
                           .collect(Collectors.toList()));
  }

  @GetMapping(path = "/ads/public", produces = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity<List<PublicAd>> publicListing() {
    SearchingAdReturn adsPublicResult = adSearcher.execute();
    return ResponseEntity
        .ok(adsPublicResult.getAds()
                           .stream()
                           .map(PublicAd::buildPublicAdFromAdFound)
                           .collect(Collectors.toList()));
  }

  @PatchMapping(path = "/ads/calculateScore")
  public ResponseEntity<Void> calculateScore() {
    return ResponseEntity.noContent().build();
  }
}
