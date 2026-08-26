package de.reticulum.sarcinae.backend.adapter.persistence.services;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import de.reticulum.sarcinae.backend.adapter.persistence.entities.EventEntity;
import de.reticulum.sarcinae.backend.adapter.persistence.mapper.EventEntityMapper;
import de.reticulum.sarcinae.backend.adapter.persistence.mapper.EventParticipantEntityMapper;
import de.reticulum.sarcinae.backend.adapter.persistence.repositories.EventRepository;
import de.reticulum.sarcinae.backend.models.Event;
import de.reticulum.sarcinae.backend.models.EventCreationRequest;
import de.reticulum.sarcinae.backend.port.persistence.EventPort;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class EventAdapter implements EventPort {
  private final EventEntityMapper eventEntityMapper;
  private final EventParticipantEntityMapper eventParticipantEntityMapper;
  private final EventRepository eventRepository;

  @Override
  @Transactional(readOnly = true)
  public List<Event> findAllEvents() {
    return eventRepository.findAll()
      .stream()
      .map(eventEntityMapper::toDomain)
      .toList();
  }

  @Override
  @Transactional
  public Event createEvent(EventCreationRequest eventCreationRequest) {
    return Optional.of(eventCreationRequest)
      .map(this::toEntity)
      .map(eventRepository::save)
      .map(eventEntityMapper::toDomain)
      .orElseThrow();
  }

  private EventEntity toEntity(EventCreationRequest eventCreationRequest) {
    var participants = eventCreationRequest.participants().stream()
      .map(eventParticipantEntityMapper::fromString)
      .collect(Collectors.toSet());
    var entity = eventEntityMapper.toEntityWithoutParticipant(eventCreationRequest);
    entity.addAllParticipants(participants);
    return entity;
  }
}
