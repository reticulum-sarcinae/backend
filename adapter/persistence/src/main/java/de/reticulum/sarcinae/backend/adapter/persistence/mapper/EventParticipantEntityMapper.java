package de.reticulum.sarcinae.backend.adapter.persistence.mapper;

import de.reticulum.sarcinae.backend.adapter.persistence.entities.EventParticipantEntity;
import de.reticulum.sarcinae.backend.models.EventParticipant;
import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;
import org.mapstruct.ReportingPolicy;

@Mapper(
  componentModel = MappingConstants.ComponentModel.SPRING,
  unmappedSourcePolicy = ReportingPolicy.ERROR,
  unmappedTargetPolicy = ReportingPolicy.ERROR
)
public interface EventParticipantEntityMapper {

  @BeanMapping(ignoreUnmappedSourceProperties = {"event"})
  EventParticipant toDomain(EventParticipantEntity entity);

  @Mapping(target = "id", ignore = true)
  @Mapping(target = "event", ignore = true)
  EventParticipantEntity fromString(String name);
}
