package de.reticulum.sarcinae.backend.mapper;

import java.util.List;

import de.reticulum.sarcinae.backend.models.Event;
import de.reticulum.sarcinae.backend.models.output.EventOutput;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;
import org.mapstruct.ReportingPolicy;

@Mapper(
  componentModel = MappingConstants.ComponentModel.SPRING,
  unmappedSourcePolicy = ReportingPolicy.ERROR,
  unmappedTargetPolicy = ReportingPolicy.ERROR,
  uses = {
    EventParticipantOutputMapper.class
  }
)
public interface EventOutputMapper {

  EventOutput toOutput(Event domain);

  List<EventOutput> toOutput(List<Event> domain);
}
