package com.example;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

@SpringBootTest
@ActiveProfiles("test")
class QuotesRepositoryTest {

  @Test
  @DisplayName("A random quote is returned")
  void testRandomQuotes( @Autowired QuoteRepository quoteRepository) {
     var quote = quoteRepository.findRandomQuote();
     assertThat(quote).isNotNull();
  }
}
