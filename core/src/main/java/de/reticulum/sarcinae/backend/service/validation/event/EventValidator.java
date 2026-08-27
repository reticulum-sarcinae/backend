package de.reticulum.sarcinae.backend.service.validation.event;

import java.util.List;

import de.reticulum.sarcinae.backend.models.EventCreationRequest;

public interface EventValidator {

  List<String> validate(EventCreationRequest eventCreationRequest);
}
