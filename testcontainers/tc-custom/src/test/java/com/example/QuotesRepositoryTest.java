package com.example;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.testcontainers.containers.GenericContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;
import org.testcontainers.utility.DockerImageName;

@SpringBootTest
@Testcontainers
class QuotesRepositoryTest {


  @Container
  static GenericContainer<?> postgres = new GenericContainer<>(DockerImageName.parse("postgres:12"))
      .withEnv("POSTGRES_DB", "quotes")
      .withEnv("POSTGRES_USER","postgres")
      .withEnv("POSTGRES_PASSWORD","password")
      .withExposedPorts(5432);


  @DynamicPropertySource
  static void jdbcProperties(DynamicPropertyRegistry registry) {
    registry.add("spring.datasource.url", () ->
        "jdbc:postgresql://127.0.0.1:" + postgres.getMappedPort(5432) + "/quotes");
  }

  @Test
  @DisplayName("A random quote is returned")
  void testRandomQuotes( @Autowired QuoteRepository quoteRepository) {
     var quote = quoteRepository.findRandomQuote();
     assertThat(quote).isNotNull();
  }
}
