package com.gksoftware.poc.mongo.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RestController;

import com.gksoftware.poc.mongo.api.ConferenceApi;
import com.gksoftware.poc.mongo.dtomodel.ConferenceBodyDTO;
import com.gksoftware.poc.mongo.dtomodel.ConferenceDTO;
import com.gksoftware.poc.mongo.service.ConferenceService;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;

@RequiredArgsConstructor
@RestController
@Log4j2
@CrossOrigin("*") // allow all origins for demo
public class ConferenceController implements ConferenceApi {

  private final ConferenceService conferenceService;

  @Override
  public ResponseEntity<List<ConferenceDTO>> listConferences() {
    List<ConferenceDTO> result = conferenceService.listConferences();
    return new ResponseEntity<>(result, HttpStatus.OK);
  }

  @Override
  public ResponseEntity<ConferenceDTO> readConference(String conferenceId) {
    ConferenceDTO result = conferenceService.readConference(conferenceId);
    return result==null?new ResponseEntity<>(HttpStatus.NOT_FOUND):new ResponseEntity<>(result,HttpStatus.OK);
  }

  @Override
  public ResponseEntity<ConferenceDTO> updateConference(String conferenceId, ConferenceBodyDTO conferenceDTO) {
    ConferenceDTO result = conferenceService.updateConference(conferenceId, conferenceDTO);
    return result==null?new ResponseEntity<>(HttpStatus.NOT_FOUND):new ResponseEntity<>(result,HttpStatus.OK);
  }

  @Override
  public ResponseEntity<ConferenceDTO> createConference(ConferenceBodyDTO conferenceDTO) {
    return new ResponseEntity<ConferenceDTO>(conferenceService.createConference(conferenceDTO), HttpStatus.CREATED);
  }
}
