package com.idealista.adrankingchallenge.infrastructure.api;

import com.idealista.adrankingchallenge.application.UseCase;
import com.idealista.adrankingchallenge.application.ad.search.SearchingAdParams;
import com.idealista.adrankingchallenge.application.ad.search.SearchingAdReturn;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AdsController {

  @Autowired
  private UseCase<SearchingAdParams, SearchingAdReturn> adSearcher;

  //TODO añade url del endpoint
  public ResponseEntity<List<QualityAd>> qualityListing() {
    //TODO rellena el cuerpo del método
    return ResponseEntity.notFound().build();
  }

  @GetMapping(path = "/ads/public", produces = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity<List<PublicAd>> publicListing() {
    SearchingAdReturn adsPublicResult = adSearcher.execute(new SearchingAdParams());
    return ResponseEntity
        .ok(adsPublicResult.getAds().stream().map(PublicAd::buildPublicAdFromAdFound).collect(
            Collectors.toList()));
  }

  //TODO añade url del endpoint
  public ResponseEntity<Void> calculateScore() {
    //TODO rellena el cuerpo del método
    return ResponseEntity.notFound().build();
  }
}
