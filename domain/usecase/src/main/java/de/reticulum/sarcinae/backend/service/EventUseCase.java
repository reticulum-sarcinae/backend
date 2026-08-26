package de.reticulum.sarcinae.backend.service;

import java.util.List;

import de.reticulum.sarcinae.backend.models.Event;
import de.reticulum.sarcinae.backend.models.EventCreationRequest;

public interface EventUseCase {

  List<Event> findAllEvents();

  Event createEvent(EventCreationRequest eventCreationRequest);
}
