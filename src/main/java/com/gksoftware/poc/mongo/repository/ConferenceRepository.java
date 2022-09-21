package com.gksoftware.poc.mongo.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;

import com.gksoftware.poc.mongo.model.Conference;
import com.gksoftware.poc.mongo.model.ConferenceStatus;

public interface ConferenceRepository extends MongoRepository<Conference, String>, QuerydslPredicateExecutor<Conference> {

	Page<Conference> findByName(String name, Pageable pageable);
	
	Page<Conference> findByConferenceIdAndStatus(String conferenceId, ConferenceStatus status, Pageable pageable);
	
	@Query("{ 'name' : { $regex: ?0 } }")
	List<Conference> findConferencesByRegex(String nameRegex);
	
}
