package de.reticulum.sarcinae.backend.models;

import java.time.OffsetDateTime;
import java.util.Set;

import lombok.Builder;

@Builder
public record EventCreationRequest(
  String name,
  OffsetDateTime startTime,
  OffsetDateTime endTime,
  Set<String> participants
) {
}
