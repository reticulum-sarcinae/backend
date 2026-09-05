package de.reticulum.sarcinae.backend.service.validation.event;

import java.util.List;
import java.util.Optional;

import de.reticulum.sarcinae.backend.models.EventCreationRequest;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

@Service
public class EventNameValidator implements EventValidator {
  @Override
  public List<String> validate(EventCreationRequest eventCreationRequest) {
    var nameIsValid = Optional.ofNullable(eventCreationRequest.name())
      .map(String::trim)
      .map(StringUtils::hasText)
      .orElse(false);
    if (!nameIsValid) {
      return List.of("Event name needs to contain any non whitespace character");
    }
    return List.of();
  }
}
