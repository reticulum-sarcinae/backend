package de.reticulum.sarcinae.backend.service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import de.reticulum.sarcinae.backend.models.Event;
import de.reticulum.sarcinae.backend.models.EventCreationRequest;
import de.reticulum.sarcinae.backend.port.persistence.EventPersistencePort;
import de.reticulum.sarcinae.backend.service.validation.event.EventValidationService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class EventService implements EventUseCase {
  private final EventPersistencePort eventPersistencePort;
  private final EventValidationService eventValidationService;

  @Override
  public List<Event> findAllEvents() {
    return eventPersistencePort.findAllEvents();
  }

  @Override
  public Event createEvent(EventCreationRequest eventCreationRequest) {
    eventValidationService.validate(eventCreationRequest);
    return eventPersistencePort.createEvent(eventCreationRequest);
  }

  @Override
  public void deleteEvent(UUID eventID) {
    eventPersistencePort.deleteEventById(eventID);
  }

  @Override
  public Optional<Event> addParticipant(UUID eventId, String name) {
    return eventPersistencePort.addParticipant(eventId, name);
  }

  @Override
  public Optional<Event> deleteParticipant(UUID eventId, UUID participantId) {
    return eventPersistencePort.deleteParticipant(eventId, participantId);
  }

  @Override
  public Optional<Event> findEventById(UUID eventId) {
    return eventPersistencePort.findEventById(eventId);
  }
}
