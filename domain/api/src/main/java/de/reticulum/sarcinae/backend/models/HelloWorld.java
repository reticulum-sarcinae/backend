package de.reticulum.sarcinae.backend.models;

import lombok.Builder;

@Builder
public record HelloWorld(
  String message
) {
}
