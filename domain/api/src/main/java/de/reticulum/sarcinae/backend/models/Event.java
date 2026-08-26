package de.reticulum.sarcinae.backend.models;

import java.time.OffsetDateTime;
import java.util.Set;
import java.util.UUID;

import lombok.Builder;

@Builder(toBuilder = true)
public record Event(
  UUID id,
  String name,
  OffsetDateTime startTime,
  OffsetDateTime endTime,
  Set<EventParticipant> participants
) {
}
