package de.reticulum.sarcinae.backend.controller;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.OffsetDateTime;
import java.time.ZoneOffset;

import de.reticulum.sarcinae.backend.adapter.persistence.entities.EventEntity;
import de.reticulum.sarcinae.backend.adapter.persistence.entities.EventParticipantEntity;
import de.reticulum.sarcinae.backend.adapter.persistence.repositories.EventParticipantRepository;
import de.reticulum.sarcinae.backend.adapter.persistence.repositories.EventRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.webmvc.test.autoconfigure.AutoConfigureMockMvc;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@SpringBootTest
@AutoConfigureMockMvc
public class EventControllerMockMvcTest {

  @Autowired
  MockMvc mockMvc;

  @Autowired
  EventRepository eventRepository;

  @Autowired
  EventParticipantRepository eventParticipantRepository;

  @BeforeEach
  @AfterEach
  void cleanDb() {
    eventParticipantRepository.deleteAll();
    eventRepository.deleteAll();
  }

  @Test
  void test_controller() throws Exception {
    var event = eventRepository.save(
      EventEntity.builder()
        .name("BREEZE")
        .startTime(OffsetDateTime.of(LocalDate.of(2027, 8, 18).atStartOfDay(), ZoneOffset.UTC))
        .endTime(OffsetDateTime.of(LocalDate.of(2027, 8, 21).atStartOfDay(), ZoneOffset.UTC))
        .build()
    );
    var participant = eventParticipantRepository.save(
      EventParticipantEntity.builder()
        .name("Wirtshausfranz")
        .event(event)
        .build()
    );

    mockMvc.perform(
        get("/api/event/all")
      )
      .andExpect(status().isOk())
      .andExpect(content().json(
        """
          [
            {
              "id": "%s",
              "name": "BREEZE",
              "startTime": "2027-08-18T00:00:00Z",
              "endTime": "2027-08-21T00:00:00Z",
              "participants": [
                {
                  "id": "%s",
                  "name": "Wirtshausfranz"
                }
              ]
            }
          ]
          """.formatted(event.getId(), participant.getId())
      ));
  }
}
