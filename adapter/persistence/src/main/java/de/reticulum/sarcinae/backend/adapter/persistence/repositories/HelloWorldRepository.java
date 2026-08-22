package de.reticulum.sarcinae.backend.adapter.persistence.repositories;

import java.util.UUID;

import de.reticulum.sarcinae.backend.adapter.persistence.entities.HelloWorldEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface HelloWorldRepository extends JpaRepository<HelloWorldEntity, UUID> {
}
