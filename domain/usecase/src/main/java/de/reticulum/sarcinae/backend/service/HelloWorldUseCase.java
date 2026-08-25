package de.reticulum.sarcinae.backend.service;

import java.util.Optional;

import de.reticulum.sarcinae.backend.models.HelloWorld;

public interface HelloWorldUseCase {

  Optional<HelloWorld> helloWorld();
}
