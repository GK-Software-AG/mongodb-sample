package com.gksoftware.poc.mongo.mapper;

import java.util.Optional;

import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.ReportingPolicy;

import com.gksoftware.poc.mongo.dtomodel.ConferenceBodyDTO;
import com.gksoftware.poc.mongo.model.Conference;
import com.gksoftware.poc.mongo.dtomodel.ConferenceDTO;

@Mapper(
  componentModel = "spring",
  unmappedSourcePolicy = ReportingPolicy.IGNORE,
  unmappedTargetPolicy = ReportingPolicy.IGNORE
)
public interface ConferenceMapper {

  Conference toConference(ConferenceDTO campaignDTO);

  ConferenceDTO toConferenceDTO(Conference conference);

  Conference updateEntityFromModel(ConferenceBodyDTO conferenceDTO, @MappingTarget Conference conference);

}
