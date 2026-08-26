package de.reticulum.sarcinae.backend.adapter.persistence.mapper;

import de.reticulum.sarcinae.backend.adapter.persistence.entities.EventEntity;
import de.reticulum.sarcinae.backend.models.Event;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;
import org.mapstruct.ReportingPolicy;

@Mapper(
  componentModel = MappingConstants.ComponentModel.SPRING,
  unmappedSourcePolicy = ReportingPolicy.ERROR,
  unmappedTargetPolicy = ReportingPolicy.ERROR,
  uses = {
    EventParticipantEntityMapper.class
  }
)
public interface EventEntityMapper {

  Event toDomain(EventEntity entity);
}
