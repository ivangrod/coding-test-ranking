package com.idealista.adrankingchallenge.e2e;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.hamcrest.Matchers.hasSize;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.patch;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.idealista.adrankingchallenge.infrastructure.Main;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = Main.class)
@AutoConfigureMockMvc
@TestPropertySource(locations = "classpath:application-test.yml")
public class RetrieveQualityAdsTest {

  @Autowired
  private MockMvc mockMvc;

  @Test
  public void should_Retrieve_Quality_Ads_Ordered_By_Score() throws Exception {

    // Arrange
    mockMvc.perform(patch("/ads/calculateScore"));

    // Act
    final ResultActions response = mockMvc.perform(get("/ads/quality"));

    // Assert
    response.andExpect(status().isOk())
            .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
            .andExpect(jsonPath("$", hasSize(10)))
            .andExpect(jsonPath("$.[0]", is(notNullValue())))
            .andExpect(jsonPath("$.[9]", is(notNullValue())));
  }
}