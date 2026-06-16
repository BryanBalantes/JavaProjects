package com.commercial.logbook_app.model;

import com.commercial.logbook_app.dto.TodoDTO;
import jakarta.persistence.*;

@Entity
public class Todo {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private int id;

  private String task;

  @ManyToOne private User assignee;

  public Todo() {}

  public Todo(TodoDTO dto) {
    this.id = dto.getId();
    this.task = dto.getTask();
    this.assignee = new User(dto.getAssignee());
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

  public User getAssignee() {
    return assignee;
  }

  public void setAssignee(User assignee) {
    this.assignee = assignee;
  }
}
