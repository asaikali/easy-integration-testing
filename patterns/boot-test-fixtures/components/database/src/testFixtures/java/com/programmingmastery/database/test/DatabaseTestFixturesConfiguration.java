/*
 * Copyright 2020 Adib Saikali
 *
 */

package com.programmingmastery.database.test;

import com.programmingmastery.test.spring.TestProfile;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@TestProfile
@Configuration
class DatabaseTestFixturesConfiguration {

  @Bean
  TxUtils txUtils() {
    return new TxUtils();
  }
}
