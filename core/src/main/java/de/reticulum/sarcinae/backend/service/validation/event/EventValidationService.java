package de.reticulum.sarcinae.backend.service.validation.event;

import java.util.List;

import de.reticulum.sarcinae.backend.models.EventCreationRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class EventValidationService {
  private final List<EventValidator> validators;

  public void validate(EventCreationRequest eventCreationRequest) {
    var errors = validators.stream()
      .map(validator -> validator.validate(eventCreationRequest))
      .flatMap(List::stream)
      .toList();

    if (!errors.isEmpty()) {
      throw new IllegalArgumentException("Event creation request is invalid: " + String.join(", ", errors));
    }
  }
}
