package de.reticulum.sarcinae.backend.controller;

import java.util.List;
import java.util.Optional;

import de.reticulum.sarcinae.backend.mapper.EventInputMapper;
import de.reticulum.sarcinae.backend.mapper.EventOutputMapper;
import de.reticulum.sarcinae.backend.models.input.EventCreationRequestInput;
import de.reticulum.sarcinae.backend.models.output.EventOutput;
import de.reticulum.sarcinae.backend.service.EventUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(EventController.PATH)
@RequiredArgsConstructor
public class EventController {
  public static final String PATH = "/api/event";
  private final EventUseCase eventUseCase;
  private final EventOutputMapper outputMapper;
  private final EventInputMapper inputMapper;

  @GetMapping("/all")
  public ResponseEntity<List<EventOutput>> getAllEvents() {
    return Optional.of(eventUseCase.findAllEvents())
      .map(outputMapper::toOutput)
      .map(ResponseEntity::ok)
      .orElse(ResponseEntity.notFound().build());
  }

  @PostMapping
  public ResponseEntity<EventOutput> createEvent(
    @RequestBody EventCreationRequestInput eventCreationRequestInput
  ) {
    return Optional.of(eventCreationRequestInput)
      .map(inputMapper::toDomain)
      .map(eventUseCase::createEvent)
      .map(outputMapper::toOutput)
      .map(ResponseEntity::ok)
      .orElse(ResponseEntity.notFound().build());
  }
}
