package de.reticulum.sarcinae.backend.adapter.persistence.services;

import java.util.List;

import de.reticulum.sarcinae.backend.adapter.persistence.mapper.EventEntityMapper;
import de.reticulum.sarcinae.backend.adapter.persistence.repositories.EventRepository;
import de.reticulum.sarcinae.backend.models.Event;
import de.reticulum.sarcinae.backend.port.persistence.EventPort;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class EventAdapter implements EventPort {
  private final EventEntityMapper eventEntityMapper;
  private final EventRepository eventRepository;

  @Override
  @Transactional(readOnly = true)
  public List<Event> findAllEvents() {
    return eventRepository.findAll()
      .stream()
      .map(eventEntityMapper::toDomain)
      .toList();
  }
}
