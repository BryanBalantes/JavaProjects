package com.commercial.logbook_app.dto;

import com.commercial.logbook_app.model.Todo;

/*
 * This class holds information about the user's RECORD
 * */
public class TodoDTO {
  private int id;
  private String task;

  private UserDTO assignee;

  public TodoDTO() {}

  public TodoDTO(Todo model) {
    this.id = model.getId();
    this.task = model.getTask();
    this.assignee = new UserDTO(model.getAssignee());
  }

  public TodoDTO(int id, String task) {
    this.id = id;
    this.task = task;
  }

  public int getId() {
    return id;
  }

  public void setId(int id) {
    this.id = id;
  }

  public String getTask() {
    return task;
  }

  public void setTask(String task) {
    this.task = task;
  }

  public UserDTO getAssignee() {
    return assignee;
  }

  public void setAssignee(UserDTO assignee) {
    this.assignee = assignee;
  }
}
