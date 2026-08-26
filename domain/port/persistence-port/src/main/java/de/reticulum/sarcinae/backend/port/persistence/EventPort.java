package de.reticulum.sarcinae.backend.port.persistence;

import java.util.List;

import de.reticulum.sarcinae.backend.models.Event;
import de.reticulum.sarcinae.backend.models.EventCreationRequest;

public interface EventPort {
  List<Event> findAllEvents();

  Event createEvent(EventCreationRequest eventCreationRequest);
}
