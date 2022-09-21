package com.gksoftware.poc.mongo;

import javax.validation.constraints.NotNull;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.springframework.boot.test.util.TestPropertyValues;
import org.springframework.context.ApplicationContextInitializer;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.testcontainers.containers.MongoDBContainer;
import org.testcontainers.containers.output.ToStringConsumer;

/**
 * Helper class for run a MongoDB testcontainer
 */
@ContextConfiguration(initializers = AbstractMongoTest.Initializer.class)
public abstract class AbstractMongoTest {

  private static final MongoDBContainer MONGO_DB_CONTAINER =
    new MongoDBContainer("mongo:5.0.12").withReuse(false);

  @BeforeAll
  static void setUpAll() {
    MONGO_DB_CONTAINER.start();
  }

  @AfterAll
  static void tearDownAll() {
    if (!MONGO_DB_CONTAINER.isShouldBeReused()) {
      MONGO_DB_CONTAINER.stop();
    }
  }

  static class Initializer implements ApplicationContextInitializer<ConfigurableApplicationContext> {
    @Override
    public void initialize(@NotNull ConfigurableApplicationContext configurableApplicationContext) {
      TestPropertyValues.of(
        String.format("spring.data.mongodb.uri: %s", MONGO_DB_CONTAINER.getReplicaSetUrl())
      ).applyTo(configurableApplicationContext);
    }
  }

}
