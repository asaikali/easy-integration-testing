/*
 * Copyright 2020 Adib Saikali
 *
 */

package com.example.billboard;

import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
class QuoteController {

  private final QuoteRepository quoteRepository;
  private final Environment environment;

  public QuoteController(QuoteRepository quoteRepository, Environment environment) {
    this.quoteRepository = quoteRepository;
    this.environment = environment;
  }

  @GetMapping("/random-quote")
  Quote randomQuote() {
    Quote result = quoteRepository.findRandomQuote();
    return result;
  }
}
