package com.gksoftware.poc.mongo.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RestController;

import com.gksoftware.poc.mongo.api.ConferenceTalkApi;
import com.gksoftware.poc.mongo.dtomodel.ConferenceTalkBodyDTO;
import com.gksoftware.poc.mongo.dtomodel.ConferenceTalkDTO;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;

@RequiredArgsConstructor
@RestController
@Log4j2
@CrossOrigin("*") // allow all origins for demo
public class ConferenceTalkController implements ConferenceTalkApi {
  @Override
  public ResponseEntity<ConferenceTalkDTO> createConferenceTalk(String conferenceId,
    ConferenceTalkBodyDTO conferenceTalkDTO) {
    return ConferenceTalkApi.super.createConferenceTalk(conferenceId, conferenceTalkDTO);
  }

  @Override
  public ResponseEntity<List<ConferenceTalkDTO>> listConferenceTalks(String conferenceId) {
    return ConferenceTalkApi.super.listConferenceTalks(conferenceId);
  }

  @Override
  public ResponseEntity<ConferenceTalkDTO> readConferenceTalk(String conferenceTalkId) {
    return ConferenceTalkApi.super.readConferenceTalk(conferenceTalkId);
  }

  @Override
  public ResponseEntity<ConferenceTalkDTO> updateConferenceTalk(String conferenceTalkId,
    ConferenceTalkBodyDTO conferenceTalkDTO) {
    return ConferenceTalkApi.super.updateConferenceTalk(conferenceTalkId, conferenceTalkDTO);
  }
}
