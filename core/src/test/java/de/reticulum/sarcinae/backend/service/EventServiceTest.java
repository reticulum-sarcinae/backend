package de.reticulum.sarcinae.backend.service;

import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.List;
import java.util.stream.Stream;

import de.reticulum.sarcinae.backend.models.EventCreationRequest;
import de.reticulum.sarcinae.backend.port.persistence.EventPersistencePort;
import de.reticulum.sarcinae.backend.service.validation.event.EventNameValidator;
import de.reticulum.sarcinae.backend.service.validation.event.EventTimeValidator;
import de.reticulum.sarcinae.backend.service.validation.event.EventValidationService;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

@ExtendWith(MockitoExtension.class)
class EventServiceTest {

  @Mock
  EventPersistencePort eventPersistencePort;

  EventService underTest = new EventService(
    eventPersistencePort,
    new EventValidationService(
      List.of(
        new EventNameValidator(),
        new EventTimeValidator()
      )
    )
  );

  public static Stream<Arguments> createEvent_validation_errors() {
    return Stream.of(
      Arguments.of(
        "No name provided",
        EventCreationRequest.builder()
          .build()
      ),
      Arguments.of(
        "Invalid name",
        EventCreationRequest.builder()
          .name(" ")
          .build()
      ),
      Arguments.of(
        "starttime after endtime",
        EventCreationRequest.builder()
          .name("name")
          .startTime(LocalDateTime.MAX.atOffset(ZoneOffset.UTC))
          .endTime(LocalDateTime.MIN.atOffset(ZoneOffset.UTC))
          .build()
      )
    );
  }

  @ParameterizedTest(name = "{0}")
  @MethodSource
  void createEvent_validation_errors(String testName, EventCreationRequest request) {
    assertThatThrownBy(() -> underTest.createEvent(request)).isInstanceOf(IllegalArgumentException.class);
  }
}
