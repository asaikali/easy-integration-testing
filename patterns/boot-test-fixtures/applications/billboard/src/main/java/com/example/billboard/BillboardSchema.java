package com.example.billboard;

import com.programmingmastery.database.schema.Schema;
import org.springframework.stereotype.Component;

@Component
class BillboardSchema implements Schema {

  @Override
  public String getName() {
    return "billboard";
  }
}
