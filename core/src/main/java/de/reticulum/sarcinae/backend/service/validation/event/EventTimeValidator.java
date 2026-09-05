package de.reticulum.sarcinae.backend.service.validation.event;

import java.util.List;

import de.reticulum.sarcinae.backend.models.EventCreationRequest;
import org.springframework.stereotype.Service;

@Service
public class EventTimeValidator implements EventValidator {

  public List<String> validate(EventCreationRequest eventCreationRequest) {
    if (eventCreationRequest.startTime() == null || eventCreationRequest.endTime() == null) {
      return List.of();
    }

    if (eventCreationRequest.startTime().isAfter(eventCreationRequest.endTime())) {
      return List.of("Start time must be before end time");
    }
    return List.of();
  }
}
