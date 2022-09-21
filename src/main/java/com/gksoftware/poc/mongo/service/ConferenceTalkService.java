package com.gksoftware.poc.mongo.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.gksoftware.poc.mongo.dtomodel.ConferenceDTO;
import com.gksoftware.poc.mongo.dtomodel.ConferenceTalkBodyDTO;
import com.gksoftware.poc.mongo.dtomodel.ConferenceTalkDTO;


import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;

@Service
@RequiredArgsConstructor
@Log4j2
public class ConferenceTalkService {

  public ConferenceTalkDTO createConferenceTalk(String conferenceId, ConferenceTalkBodyDTO conferenceTalkDTO) {
    log.info("[createConferenceTalk] Create conferenceTalk {}",conferenceTalkDTO);
    throw new UnsupportedOperationException("Not yet implemented");
  }

  public ConferenceTalkDTO readConferenceTalk(String conferenceId, String conferenceTalkId) {
    log.info("[readConferenceTalk] Read conferenceTalk with Id {}",conferenceTalkId);
    throw new UnsupportedOperationException("Not yet implemented");
  }

  public List<ConferenceTalkDTO> listConferenceTalks(String conferenceId) {
    log.info("[listConferenceTalks] List conference talks for conferenceId {}",conferenceId);
    throw new UnsupportedOperationException("Not yet implemented");
  }

  public ConferenceDTO updateConferenceTalk(String conferenceTalkId, ConferenceTalkBodyDTO conferenceTalkDTO) {
    log.info("[updateConferenceTalk] Update conferenceTalk with Id {}",conferenceTalkId);
    throw new UnsupportedOperationException("Not yet implemented");
  }

}
