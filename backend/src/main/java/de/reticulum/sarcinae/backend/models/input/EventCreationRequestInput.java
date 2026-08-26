package de.reticulum.sarcinae.backend.models.input;

import java.time.OffsetDateTime;
import java.util.Set;

import lombok.Builder;

@Builder
public record EventCreationRequestInput(
  String name,
  OffsetDateTime startTime,
  OffsetDateTime endTime,
  Set<String> participants
) {
}
