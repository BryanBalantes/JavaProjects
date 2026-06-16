package com.commercial.logbook_app.config;

/*
 * This is part of security authentication / authorization
 * */
public enum USER_TYPE {
  GENERAL("GENERAL"),

  ADMIN("ADMIN");

  private String type;

  USER_TYPE(String type) {
    this.type = type;
  }

  public String getType() {
    return type;
  }
}
