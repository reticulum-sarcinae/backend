package de.reticulum.sarcinae.backend.service;

import java.util.List;

import de.reticulum.sarcinae.backend.models.Event;
import de.reticulum.sarcinae.backend.port.persistence.EventPort;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class EventService implements EventUseCase {
  private final EventPort eventPort;

  @Override
  public List<Event> findAllEvents() {
    return eventPort.findAllEvents();
  }
}
