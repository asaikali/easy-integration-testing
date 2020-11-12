/*
 * Copyright 2020 Adib Saikali
 *
 */

package com.programmingmastery.test.spring;

public interface DatabaseManager {

  void dropSchemas();

  void rebuildSchemasAndLoadTestData();
}
