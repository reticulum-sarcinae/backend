package de.reticulum.sarcinae.backend.adapter.persistence.mapper;

import de.reticulum.sarcinae.backend.adapter.persistence.entities.HelloWorldEntity;
import de.reticulum.sarcinae.backend.models.HelloWorld;
import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;
import org.mapstruct.ReportingPolicy;

@Mapper(
  componentModel = MappingConstants.ComponentModel.SPRING,
  unmappedSourcePolicy = ReportingPolicy.ERROR,
  unmappedTargetPolicy = ReportingPolicy.ERROR
)
public interface HelloWorldEntityMapper {

  @BeanMapping(ignoreUnmappedSourceProperties = {"id"})
  HelloWorld toDomain(HelloWorldEntity entity);
}
