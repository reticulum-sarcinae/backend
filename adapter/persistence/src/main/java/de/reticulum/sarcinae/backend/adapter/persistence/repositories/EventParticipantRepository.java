package de.reticulum.sarcinae.backend.adapter.persistence.repositories;

import java.util.List;
import java.util.UUID;

import de.reticulum.sarcinae.backend.adapter.persistence.entities.EventEntity;
import de.reticulum.sarcinae.backend.adapter.persistence.entities.EventParticipantEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EventParticipantRepository extends JpaRepository<EventParticipantEntity, UUID> {

  List<EventParticipantEntity> findAllByEvent(EventEntity event);
}
