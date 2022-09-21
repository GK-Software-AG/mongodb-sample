package com.gksoftware.poc.mongo.model;

import lombok.Data;

@Data
public class Location {
  private String name;
  private String street;
  private String country;
  private String city;
  private String zipCode;
}
