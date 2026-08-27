package de.reticulum.sarcinae.backend.port.persistence;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import de.reticulum.sarcinae.backend.models.Event;
import de.reticulum.sarcinae.backend.models.EventCreationRequest;

public interface EventPort {
  List<Event> findAllEvents();

  Event createEvent(EventCreationRequest eventCreationRequest);

  void deleteEventById(UUID eventID);

  Optional<Event> addParticipant(UUID eventId, String name);

  Optional<Event> deleteParticipant(UUID eventId, UUID participantId);

  Optional<Event> findEventById(UUID eventId);
}
