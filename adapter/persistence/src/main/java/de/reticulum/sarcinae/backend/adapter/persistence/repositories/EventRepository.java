package de.reticulum.sarcinae.backend.adapter.persistence.repositories;

import java.util.UUID;

import de.reticulum.sarcinae.backend.adapter.persistence.entities.EventEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EventRepository extends JpaRepository<EventEntity, UUID> {
}
