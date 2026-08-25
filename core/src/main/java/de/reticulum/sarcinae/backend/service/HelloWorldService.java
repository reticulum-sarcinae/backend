package de.reticulum.sarcinae.backend.service;

import java.util.Optional;

import de.reticulum.sarcinae.backend.models.HelloWorld;
import de.reticulum.sarcinae.backend.port.persistence.HelloWorldPort;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class HelloWorldService implements HelloWorldUseCase {
  private final HelloWorldPort helloWorldPort;

  @Override
  public Optional<HelloWorld> helloWorld() {
    return helloWorldPort.helloWorldUseCase();
  }
}
