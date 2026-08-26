package de.reticulum.sarcinae.backend.service;

import java.util.List;

import de.reticulum.sarcinae.backend.models.Event;

public interface EventUseCase {

  List<Event> findAllEvents();
}
