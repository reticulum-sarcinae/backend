package de.reticulum.sarcinae.backend.controller;

import de.reticulum.sarcinae.backend.models.HelloWorld;
import de.reticulum.sarcinae.backend.service.HelloWorldUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(HelloWorldController.PATH)
@RequiredArgsConstructor
public class HelloWorldController {
  public static final String PATH = "/api";
  private final HelloWorldUseCase helloWorldUseCase;

  @GetMapping("/hello-world")
  public ResponseEntity<String> helloWorld() {
    return helloWorldUseCase.helloWorld()
      .map(HelloWorld::message)
      .map(ResponseEntity::ok)
      .orElse(ResponseEntity.notFound().build());
  }
}
