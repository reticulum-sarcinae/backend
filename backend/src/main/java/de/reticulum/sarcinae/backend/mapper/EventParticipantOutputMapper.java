package de.reticulum.sarcinae.backend.mapper;

import de.reticulum.sarcinae.backend.models.EventParticipant;
import de.reticulum.sarcinae.backend.models.EventParticipantOutput;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;
import org.mapstruct.ReportingPolicy;

@Mapper(
  componentModel = MappingConstants.ComponentModel.SPRING,
  unmappedSourcePolicy = ReportingPolicy.ERROR,
  unmappedTargetPolicy = ReportingPolicy.ERROR
)
public interface EventParticipantOutputMapper {

  EventParticipantOutput toOutput(EventParticipant entity);
}
