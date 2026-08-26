package de.reticulum.sarcinae.backend.service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import de.reticulum.sarcinae.backend.models.Event;
import de.reticulum.sarcinae.backend.models.EventCreationRequest;

public interface EventUseCase {

  List<Event> findAllEvents();

  Event createEvent(EventCreationRequest eventCreationRequest);

  void deleteEvent(UUID eventId);

  Optional<Event> addParticipant(UUID eventId, String name);

  Optional<Event> deleteParticipant(UUID eventId, UUID participantId);
}
