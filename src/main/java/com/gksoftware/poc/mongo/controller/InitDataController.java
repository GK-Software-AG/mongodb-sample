package com.gksoftware.poc.mongo.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RestController;

import com.gksoftware.poc.mongo.api.InitApi;
import com.gksoftware.poc.mongo.dtomodel.InitData200ResponseDTO;
import com.gksoftware.poc.mongo.dtomodel.InitDataRequestDTO;
import com.gksoftware.poc.mongo.service.InitService;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;

@RequiredArgsConstructor
@RestController
@Log4j2
@CrossOrigin("*") // allow all origins for demo
public class InitDataController implements InitApi {

  private final InitService initService;

  @Override
  public ResponseEntity<InitData200ResponseDTO> initData(InitDataRequestDTO initDataRequestDTO) {
    Long created = initService.initConferenceData(initDataRequestDTO.getYears());
    InitData200ResponseDTO result = new InitData200ResponseDTO();
    result.setTotal(created);
    return new ResponseEntity<>(result, HttpStatus.OK);
  }
}
