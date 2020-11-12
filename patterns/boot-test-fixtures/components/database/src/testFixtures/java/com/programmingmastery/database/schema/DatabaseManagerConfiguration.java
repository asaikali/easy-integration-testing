/*
 * Copyright 2020 Adib Saikali
 *
 */

package com.programmingmastery.database.schema;

import com.programmingmastery.database.test.TestDataLoader;
import com.programmingmastery.test.spring.DatabaseManager;
import com.programmingmastery.test.spring.TestProfile;
import java.util.List;
import java.util.Optional;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.JdbcTemplate;

@TestProfile
@Configuration
class DatabaseManagerConfiguration {

  @Bean
  DatabaseManager databaseManager(
      SchemaManagerImpl schemaManager,
      Optional<List<TestDataLoader>> optionalLoaders,
      JdbcTemplate jdbcTemplate) {

    return new TestDatabaseManager(schemaManager, optionalLoaders, jdbcTemplate);
  }
}
