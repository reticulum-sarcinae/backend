package de.reticulum.sarcinae.backend.adapter.persistence.services;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
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

  @Override
  public void deleteEventById(UUID eventID) {
    eventRepository.deleteById(eventID);
  }

  @Override
  public Optional<Event> addParticipant(UUID eventId, String name) {
    return eventRepository.findById(eventId)
      .map(event -> {
        event.addParticipant(eventParticipantEntityMapper.fromString(name));
        return event;
      })
      .map(eventRepository::save)
      .map(eventEntityMapper::toDomain);
  }

  @Override
  public Optional<Event> deleteParticipant(UUID eventId, UUID participantId) {
    return eventRepository.findById(eventId)
      .flatMap(event -> removeParticipantById(event, participantId))
      .map(eventRepository::save)
      .map(eventEntityMapper::toDomain);
  }

  @Override
  public Optional<Event> findEventById(UUID eventId) {
    return eventRepository.findById(eventId)
      .map(eventEntityMapper::toDomain);
  }

  private Optional<EventEntity> removeParticipantById(EventEntity event, UUID participantId) {
    return event.getParticipants().stream()
      .filter(participant -> participant.getId().equals(participantId))
      .findFirst()
      .map(participant -> {
        event.removeParticipant(participant);
        return event;
      });
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
