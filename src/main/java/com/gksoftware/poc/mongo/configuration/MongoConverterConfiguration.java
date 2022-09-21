package com.gksoftware.poc.mongo.configuration;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.convert.converter.Converter;
import org.springframework.data.mongodb.core.convert.MongoCustomConversions;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;

@Configuration
@RequiredArgsConstructor
@Log4j2
@PropertySource(ModelProperties.MODEL_PROPERTY_SOURCE)
@Import(TimeConfiguration.class)
public class MongoConverterConfiguration {

  @Bean
  MongoCustomConversions mongoCustomConversions(DateReadConverter dateReadConverter,
      DateWriteConverter dateWriteConverter) {
    List<Converter<?, ?>> converters = List.of(
        dateReadConverter,
        dateWriteConverter);

    log.info("registered convertors: {}",
        converters.stream().map(convertor -> convertor.getClass().getCanonicalName()).collect(Collectors.joining(",")));
    return new MongoCustomConversions(converters);
  }

  @Bean
  public DateReadConverter dateReadConverter(TimeProperties timeProperties) {
    return new DateReadConverter(timeProperties);
  }

  @Bean
  public DateWriteConverter dateWriteConverter() {
    return new DateWriteConverter();
  }



}
