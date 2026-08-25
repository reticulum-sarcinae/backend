package de.reticulum.sarcinae.backend.adapter.persistence.services;

import java.util.Optional;

import de.reticulum.sarcinae.backend.adapter.persistence.mapper.HelloWorldEntityMapper;
import de.reticulum.sarcinae.backend.adapter.persistence.repositories.HelloWorldRepository;
import de.reticulum.sarcinae.backend.models.HelloWorld;
import de.reticulum.sarcinae.backend.port.persistence.HelloWorldPort;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class HelloWorldAdapter implements HelloWorldPort {
  private final HelloWorldRepository helloWorldRepository;
  private final HelloWorldEntityMapper helloWorldEntityMapper;

  @Override
  @Transactional(readOnly = true)
  public Optional<HelloWorld> helloWorldUseCase() {
    return helloWorldRepository.findAll()
      .stream()
      .findAny()
      .map(helloWorldEntityMapper::toDomain);
  }
}
