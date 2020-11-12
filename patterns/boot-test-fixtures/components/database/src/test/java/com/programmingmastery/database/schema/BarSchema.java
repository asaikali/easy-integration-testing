/*
 * Copyright 2020 Adib Saikali
 *
 */

package com.programmingmastery.database.schema;

import org.springframework.stereotype.Component;

@Component
class BarSchema implements Schema {

  @Override
  public String getName() {
    return "bar";
  }
}
