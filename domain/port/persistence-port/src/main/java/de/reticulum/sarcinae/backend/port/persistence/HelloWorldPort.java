package de.reticulum.sarcinae.backend.port.persistence;

import java.util.Optional;

import de.reticulum.sarcinae.backend.models.HelloWorld;

public interface HelloWorldPort {
  Optional<HelloWorld> helloWorldUseCase();
}
