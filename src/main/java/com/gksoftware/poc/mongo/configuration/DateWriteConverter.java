package com.gksoftware.poc.mongo.configuration;

import java.time.OffsetDateTime;
import java.util.Date;

import org.springframework.core.convert.converter.Converter;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class DateWriteConverter implements Converter<OffsetDateTime, Date> {

  /**
   * Convert {@link OffsetDateTime} to a {@link Date} preserving the same instant
   * in time
   *
   * @param offsetDateTime offsetDateTime
   * @return a converted Date
   */
  @Override
  public Date convert(OffsetDateTime offsetDateTime) {
    return Date.from(offsetDateTime.toInstant());
  }
}
