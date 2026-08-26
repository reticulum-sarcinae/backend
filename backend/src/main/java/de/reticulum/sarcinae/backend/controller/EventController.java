package de.reticulum.sarcinae.backend.controller;

import java.util.List;
import java.util.Optional;

import de.reticulum.sarcinae.backend.mapper.EventOutputMapper;
import de.reticulum.sarcinae.backend.models.EventOutput;
import de.reticulum.sarcinae.backend.service.EventUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(EventController.PATH)
@RequiredArgsConstructor
public class EventController {
  public static final String PATH = "/api";
  private final EventUseCase eventUseCase;
  private final EventOutputMapper eventOutputMapper;

  @GetMapping("/event/all")
  public ResponseEntity<List<EventOutput>> getAllEvents() {
    return Optional.of(eventUseCase.findAllEvents())
      .map(eventOutputMapper::toOutput)
      .map(ResponseEntity::ok)
      .orElse(ResponseEntity.notFound().build());
  }
}
