package de.reticulum.sarcinae.backend.controller;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.webmvc.test.autoconfigure.AutoConfigureMockMvc;
import org.springframework.test.web.servlet.MockMvc;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@SpringBootTest
@AutoConfigureMockMvc
public class HelloWorldControllerMockMvcTest {

  @Autowired
  MockMvc mockMvc;

  @BeforeEach
  @AfterEach
  void cleanDb() {
  }

  @Test
  void test_controller() throws Exception {
    var response = mockMvc.perform(
        get("/api/hello-world")
      )
      .andExpect(status().isOk())
      .andReturn()
      .getResponse()
      .getContentAsString();

    assertThat(response).isEqualTo("Hello World!");
  }
}
