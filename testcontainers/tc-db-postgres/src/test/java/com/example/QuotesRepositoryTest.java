package com.example;

import static org.assertj.core.api.Assertions.assertThat;
import static org.testcontainers.DockerClientFactory.TESTCONTAINERS_LABEL;

import com.github.dockerjava.api.model.Container;
import java.util.Map;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.testcontainers.DockerClientFactory;

@SpringBootTest
@ActiveProfiles("test")
class QuotesRepositoryTest {

  @BeforeEach
  void debug() {
    // print a list of all the containers test containers are currently running
    var client = DockerClientFactory.instance().client();
    var containers = client.listContainersCmd()
        .withLabelFilter(Map.of(TESTCONTAINERS_LABEL, "true")).exec();
    for (Container container : containers) {
      System.out.println(container.getImage());
    }
  }

  @Test
  @DisplayName("A random quote is returned")
  void testRandomQuotes(@Autowired QuoteRepository quoteRepository) {
    var quote = quoteRepository.findRandomQuote();
    assertThat(quote).isNotNull();
  }

  @Test
  @DisplayName("A random quote is returned")
  void testRandomQuotes2(@Autowired QuoteRepository quoteRepository) {
    var quote = quoteRepository.findRandomQuote();
    assertThat(quote).isNotNull();
  }
}
