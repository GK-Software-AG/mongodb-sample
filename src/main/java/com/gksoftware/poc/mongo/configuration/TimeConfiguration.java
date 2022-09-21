package com.gksoftware.poc.mongo.configuration;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class TimeConfiguration {

  @Bean
  @ConfigurationProperties("mongo.time")
  public TimeProperties timeProperties() {
    return new TimeProperties();
  }
}
