package com.gksoftware.poc.mongo.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.gksoftware.poc.mongo.dtomodel.ConferenceBodyDTO;
import com.gksoftware.poc.mongo.dtomodel.ConferenceDTO;
import com.gksoftware.poc.mongo.mapper.ConferenceMapper;
import com.gksoftware.poc.mongo.model.Conference;
import com.gksoftware.poc.mongo.repository.ConferenceRepository;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;

@Service
@RequiredArgsConstructor
@Log4j2
public class ConferenceService {

  private final ConferenceRepository conferenceRepository;
  private final ConferenceMapper conferenceMapper;

  public ConferenceDTO createConference(ConferenceBodyDTO conferenceDTO) {
    log.info("[createConference] Create conference {}",conferenceDTO);
    Conference conference = conferenceMapper.updateEntityFromModel(conferenceDTO, new Conference());
    Conference saved = conferenceRepository.save(conference);
    return conferenceMapper.toConferenceDTO(saved);
  }

  public ConferenceDTO readConference(String conferenceId) {
    log.info("[readConference] Read conference with Id {}",conferenceId);
    Optional<Conference> result = conferenceRepository.findById(conferenceId);
    return result.isEmpty()?null:conferenceMapper.toConferenceDTO(result.get());
  }

  public ConferenceDTO updateConference(String conferenceId, ConferenceBodyDTO conferenceDTO) {
    log.info("[updateConference] Update conference with Id {}",conferenceId);
    Optional<Conference> entity = conferenceRepository.findById(conferenceId);
    if(entity.isEmpty()) {
      log.info("[updateConference] Conference with Id {} not found",conferenceId);
      return null;
    }
    Conference conference = conferenceMapper.updateEntityFromModel(conferenceDTO,entity.get());
    conference = conferenceRepository.save(conference);
    return conferenceMapper.toConferenceDTO(conference);
  }

  public List<ConferenceDTO> listConferences() {
    log.info("[listConferences] List conferences");
    List<Conference> conferences = conferenceRepository.findAll();
    return conferences.stream().map(conferenceMapper::toConferenceDTO).collect(Collectors.toList());
  }
}
