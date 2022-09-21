package com.gksoftware.poc.mongo;

import static org.springframework.data.mongodb.core.query.Criteria.where;
import static org.springframework.data.mongodb.core.query.Query.query;

import java.security.SecureRandom;
import java.time.OffsetDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;

import org.jetbrains.annotations.NotNull;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.mongodb.core.FluentMongoOperations;
import org.springframework.data.mongodb.core.MongoTemplate;

import com.gksoftware.poc.mongo.model.Conference;
import com.gksoftware.poc.mongo.model.ConferenceStatus;
import com.gksoftware.poc.mongo.model.QConference;
import com.gksoftware.poc.mongo.repository.ConferenceRepository;
import com.querydsl.core.types.dsl.BooleanExpression;

@SpringBootTest
class MongoSampleApplicationTests extends AbstractMongoTest{

	@Autowired
	private ConferenceRepository conferenceRepository;
	
	@Autowired
	private FluentMongoOperations ops;
	
	@Autowired
	private MongoTemplate template;
	
	@Test
	void queryTest() {
		createConference("GK Product Conference 2022", "2022-09-21T10:00:00+01:00", "2022-09-22T15:00:00+01:00", "GKPRD2022", "cloud", "product", "intern" );
		createConference("GK Product Conference 2021", "2021-09-16T10:00:00+01:00", "2021-09-17T16:00:00+01:00", "GKPRD2021", "cloud", "product", "intern" );


		
		System.out.println("*** Query by Repository ***");
		Page<Conference> pageResult = conferenceRepository.findByName("GK Product Conference 2022", null);
		pageResult.forEach((c) -> System.out.println(c));
		
		System.out.println("*** Query by Regex ***");
		List<Conference> rxResult = conferenceRepository.findConferencesByRegex("GK.*");
		rxResult.forEach((c) -> System.out.println(c));
		
		System.out.println("*** Query by FluentOps ***");
		List<Conference> opsResult = ops.query(Conference.class).matching(query(where("name").is("GK Product Conference 2022"))).all();
		opsResult.stream().forEach((c) -> System.out.println(c));
		
		System.out.println("*** Query by QueryDSL ***");
		BooleanExpression query = QConference.conference.name.startsWith("GK");
		Iterable<Conference> qResult = conferenceRepository.findAll(query);
		for(Conference c:qResult) {
			System.out.println(c);
		}
	}

	@NotNull
	private Conference createConference(String name, String startDate, String endDate, String conferenceId, String... tags) {
		Conference conference = new Conference();
		conference.setName(name);
		conference.setStartDate(OffsetDateTime.parse(startDate, DateTimeFormatter.ISO_OFFSET_DATE_TIME));
		conference.setEndDate(OffsetDateTime.parse(endDate, DateTimeFormatter.ISO_OFFSET_DATE_TIME));
		conference.setStatus(ConferenceStatus.DRAFT);
		conference.setConferenceId(conferenceId);
		conference.setTags(Arrays.asList(tags));
		return conferenceRepository.save(conference);
	}

}


