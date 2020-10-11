package com.idealista.adrankingchallenge.e2e;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.patch;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.idealista.adrankingchallenge.infrastructure.Main;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = Main.class)
@AutoConfigureMockMvc
@TestPropertySource(locations = "classpath:application-test.yml")
public class CalculateScoreAdsTest {

  @Autowired
  private MockMvc mockMvc;

  @Test
  public void should_Retrieve_Public_Ads_Ordered_By_Score() throws Exception {

    // Arrange

    // Act
    final ResultActions response = mockMvc.perform(patch("/ads/calculateScore"));

    // Assert
    response.andExpect(status().isNoContent());
  }
}
