package de.reticulum.sarcinae.backend.models.output;

import java.util.UUID;

import lombok.Builder;

@Builder(toBuilder = true)
public record EventParticipantOutput(
  UUID id,
  String name
) {
}
