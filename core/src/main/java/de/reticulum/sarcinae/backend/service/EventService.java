package de.reticulum.sarcinae.backend.service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import de.reticulum.sarcinae.backend.models.Event;
import de.reticulum.sarcinae.backend.models.EventCreationRequest;
import de.reticulum.sarcinae.backend.port.persistence.EventPort;
import de.reticulum.sarcinae.backend.service.validation.event.EventValidationService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class EventService implements EventUseCase {
  private final EventPort eventPort;
  private final EventValidationService eventValidationService;

  @Override
  public List<Event> findAllEvents() {
    return eventPort.findAllEvents();
  }

  @Override
  public Event createEvent(EventCreationRequest eventCreationRequest) {
    eventValidationService.validate(eventCreationRequest);
    return eventPort.createEvent(eventCreationRequest);
  }

  @Override
  public void deleteEvent(UUID eventID) {
    eventPort.deleteEventById(eventID);
  }

  @Override
  public Optional<Event> addParticipant(UUID eventId, String name) {
    return eventPort.addParticipant(eventId, name);
  }

  @Override
  public Optional<Event> deleteParticipant(UUID eventId, UUID participantId) {
    return eventPort.deleteParticipant(eventId, participantId);
  }

  @Override
  public Optional<Event> findEventById(UUID eventId) {
    return eventPort.findEventById(eventId);
  }
}
