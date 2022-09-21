package com.gksoftware.poc.mongo.model;

import java.time.OffsetDateTime;
import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.CompoundIndex;
import org.springframework.data.mongodb.core.index.CompoundIndexes;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import com.querydsl.core.annotations.QueryEntity;

import lombok.Data;

@QueryEntity
@Document
@Data
@CompoundIndexes({
    @CompoundIndex(name = "locationName", def = "{ 'location.name': 1 }"),
	  @CompoundIndex(name = "startDateEndDate", def = "{ 'startDate': 1, 'endDate': 1  }", unique = false)
})
public class Conference {
	
	@Id
	private String id;	
	
	@Indexed
	private String name;
	private ConferenceStatus status;
	@Indexed(unique = true)
	private String conferenceId;
	private String description;
	private OffsetDateTime startDate;
	private OffsetDateTime endDate;
	private Location location;
	
	@Indexed
	private List<String> tags;


}
