package de.reticulum.sarcinae.backend.port.persistence;

import java.util.List;

import de.reticulum.sarcinae.backend.models.Event;

public interface EventPort {
  List<Event> findAllEvents();
}
