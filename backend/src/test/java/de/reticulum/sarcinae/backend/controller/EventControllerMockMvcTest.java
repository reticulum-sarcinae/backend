package de.reticulum.sarcinae.backend.controller;

import java.time.LocalDate;
import java.time.OffsetDateTime;
import java.time.ZoneOffset;
import java.util.List;
import java.util.Set;

import de.reticulum.sarcinae.backend.adapter.persistence.entities.EventEntity;
import de.reticulum.sarcinae.backend.adapter.persistence.entities.EventParticipantEntity;
import de.reticulum.sarcinae.backend.adapter.persistence.repositories.EventParticipantRepository;
import de.reticulum.sarcinae.backend.adapter.persistence.repositories.EventRepository;
import de.reticulum.sarcinae.backend.models.input.EventCreationRequestInput;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.webmvc.test.autoconfigure.AutoConfigureMockMvc;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import tools.jackson.databind.ObjectMapper;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
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
  ObjectMapper objectMapper;

  @Autowired
  EventParticipantRepository eventParticipantRepository;

  @BeforeEach
  @AfterEach
  void cleanDb() {
    eventParticipantRepository.deleteAll();
    eventRepository.deleteAll();
  }

  @Test
  void test_get_all() throws Exception {
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

  @Test
  void test_create() throws Exception {
    var request = EventCreationRequestInput.builder()
      .name("BREEZE")
      .startTime(OffsetDateTime.of(LocalDate.of(2027, 8, 18).atStartOfDay(), ZoneOffset.UTC))
      .endTime(OffsetDateTime.of(LocalDate.of(2027, 8, 21).atStartOfDay(), ZoneOffset.UTC))
      .participants(Set.of("Wirtshausfranz"))
      .build();

    mockMvc.perform(
        post("/api/event")
          .contentType(MediaType.APPLICATION_JSON)
          .content(objectMapper.writeValueAsString(request))
      )
      .andExpect(status().isOk());

    assertThat(eventRepository.findAll())
      .singleElement()
      .satisfies(eventEntity -> {
        assertThat(eventEntity.getName()).isEqualTo("BREEZE");
        assertThat(eventEntity.getStartTime()).isEqualTo(OffsetDateTime.of(LocalDate.of(2027, 8, 18).atStartOfDay(), ZoneOffset.UTC));
        assertThat(eventEntity.getEndTime()).isEqualTo(OffsetDateTime.of(LocalDate.of(2027, 8, 21).atStartOfDay(), ZoneOffset.UTC));
        assertThat(eventParticipantRepository.findAllByEvent(eventEntity))
          .singleElement()
          .satisfies(participantEntity -> assertThat(participantEntity.getName()).isEqualTo("Wirtshausfranz"));
      });
  }
}
