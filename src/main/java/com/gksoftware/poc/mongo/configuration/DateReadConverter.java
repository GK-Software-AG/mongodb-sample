package com.gksoftware.poc.mongo.configuration;

import java.time.OffsetDateTime;
import java.time.ZoneId;
import java.time.ZoneOffset;
import java.util.Date;

import org.springframework.core.convert.converter.Converter;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class DateReadConverter implements Converter<Date, OffsetDateTime> {

  private final TimeProperties timeProperties;

  /**
   * Convert a {@link Date} to {@link OffsetDateTime} with {@link ZoneOffset} set
   * to the default offset.
   *
   * @param date date
   * @return converted Date set in UTC
   */
  @Override
  public OffsetDateTime convert(Date date) {
    ZoneOffset zoneOffset = ZoneId.of(timeProperties.getDefaultZoneId()).getRules().getOffset(date.toInstant());
    return date.toInstant().atOffset(zoneOffset);
  }
}
