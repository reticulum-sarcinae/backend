package de.reticulum.sarcinae.backend.adapter.persistence.services;

import de.reticulum.sarcinae.backend.adapter.persistence.entities.HelloWorldEntity;
import de.reticulum.sarcinae.backend.adapter.persistence.repositories.HelloWorldRepository;
import de.reticulum.sarcinae.backend.port.persistence.HelloWorldPort;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class HelloWorldAdapter implements HelloWorldPort {
  private final HelloWorldRepository helloWorldRepository;

  @Override
  @Transactional(readOnly = true)
  public String helloWorldUseCase() {
    return helloWorldRepository.findAll()
      .stream()
      .findAny()
      .map(HelloWorldEntity::getMessage)
      .orElse(null);
  }
}
