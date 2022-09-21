package com.gksoftware.poc.mongo.service;

import java.time.OffsetDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.List;

import javax.validation.constraints.NotNull;

import org.springframework.stereotype.Service;

import com.gksoftware.poc.mongo.model.Conference;
import com.gksoftware.poc.mongo.model.ConferenceStatus;
import com.gksoftware.poc.mongo.model.Location;
import com.gksoftware.poc.mongo.repository.ConferenceRepository;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;

@Service
@RequiredArgsConstructor
@Log4j2
public class InitService {
  private final ConferenceRepository conferenceRepository;

  public Long initConferenceData(List<Integer> years) {
    log.info("[initConferenceData] Create conferences for the years {}",years);
    for(Integer year:years) {
      createConference("GK Product Conference "+year, year.toString()+"-09-21T10:00:00+01:00", year.toString()+"-09-22T15:00:00+01:00", "GKPRD"+year, "Hotel City","Pilsen","Czech Republic","cloud", "product", "intern" );
      createConference("GK Roadshow "+year, year.toString()+"-05-20T10:00:00+01:00", year.toString()+"-05-20T18:00:00+01:00", "GKRD"+year, "Campus","Schoeneck","Germany", "roadmap", "product", "intern" );
      createConference("Hackathon "+year, year.toString()+"-10-10T09:30:00+01:00", year.toString()+"-10-11T19:30:00+01:00", "HACK"+year, "Showroom","Berlin","Germany", "hackathon", "product", "partner" );
      createConference("Innovation Days "+year, year.toString()+"-03-09T11:00:00+01:00", year.toString()+"-03-09T16:30:00+01:00", "INNO"+year, "Headquarter","Schoeneck","Germany", "innovation", "customer" );
      createConference("KubeCon Europe "+year, year.toString()+"-04-10T09:00:00+01:00", year.toString()+"-04-13T17:30:00+01:00", "KUBE"+year, "Congress Hotel","Barcelona","Spain", "cloud", "external" );
      createConference("MongoDB Local "+year, year.toString()+"-06-26T09:00:00+01:00", year.toString()+"-06-27T17:00:00+01:00", "MONGOLOCAL"+year, "Meeting Hall","Frankfurt","Germany", "nosql", "external" );
      createConference("Meetup Q1 "+year, year.toString()+"-01-09T14:00:00+01:00", year.toString()+"-01-09T18:00:00+01:00", "MEETQ1"+year, "Virtual",null,null, "meetup", "internal", "virtual" );
      createConference("Meetup Q2 "+year, year.toString()+"-04-09T14:00:00+01:00", year.toString()+"-04-09T18:00:00+01:00", "MEETQ2"+year, "Virtual",null,null, "meetup", "internal", "virtual" );
      createConference("Meetup Q3 "+year, year.toString()+"-07-09T14:00:00+01:00", year.toString()+"-07-09T18:00:00+01:00", "MEETQ3"+year, "Virtual",null,null, "meetup", "internal", "virtual" );
      createConference("Meetup Q4 "+year, year.toString()+"-12-13T14:00:00+01:00", year.toString()+"-12-13T18:00:00+01:00", "MEETQ4"+year, "Virtual",null,null, "meetup", "internal", "virtual" );
    }
    return conferenceRepository.count();
  }


  @NotNull
  private Conference createConference(String name, String startDate, String endDate, String conferenceId, String locationName, String locationCity, String locationCountry, String... tags) {
    Conference conference = new Conference();
    conference.setName(name);
    conference.setStartDate(OffsetDateTime.parse(startDate, DateTimeFormatter.ISO_OFFSET_DATE_TIME));
    conference.setEndDate(OffsetDateTime.parse(endDate, DateTimeFormatter.ISO_OFFSET_DATE_TIME));
    if(OffsetDateTime.now().isAfter(conference.getEndDate())) {
      conference.setStatus(ConferenceStatus.DONE);
    } else if(OffsetDateTime.now().getYear()==conference.getStartDate().getYear()) {
      conference.setStatus(ConferenceStatus.PLANNED);
    } else {
      conference.setStatus(ConferenceStatus.DRAFT);
    }
    conference.setConferenceId(conferenceId);
    Location location = new Location();
    location.setName(locationName);
    location.setCity(locationCity);
    location.setCountry(locationCountry);
    conference.setLocation(location);
    conference.setTags(Arrays.asList(tags));
    return conferenceRepository.save(conference);
  }
}
