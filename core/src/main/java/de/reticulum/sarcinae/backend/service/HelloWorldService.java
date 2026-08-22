package de.reticulum.sarcinae.backend.service;

import de.reticulum.sarcinae.backend.port.persistence.HelloWorldPort;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class HelloWorldService implements HelloWorldUseCase {
  private final HelloWorldPort helloWorldPort;

  @Override
  public String helloWorld() {
    return helloWorldPort.helloWorldUseCase();
  }
}
