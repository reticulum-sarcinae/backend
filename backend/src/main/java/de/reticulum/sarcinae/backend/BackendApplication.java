package de.reticulum.sarcinae.backend;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

@EnableConfigurationProperties
@SpringBootApplication
public class BackendApplication extends SpringBootServletInitializer {

  static void main(String[] args) {
    SpringApplication.run(BackendApplication.class, args);
  }

}
