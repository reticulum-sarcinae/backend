package de.reticulum.sarcinae.backend.models;

import java.util.UUID;

import lombok.Builder;

@Builder(toBuilder = true)
public record EventParticipant(
  UUID id,
  String name
) {
}
