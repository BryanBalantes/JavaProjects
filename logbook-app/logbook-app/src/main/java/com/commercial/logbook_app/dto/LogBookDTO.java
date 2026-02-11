package com.commercial.logbook_app.dto;

import com.commercial.logbook_app.model.LogBook;

/*
* This class holds information about the user's RECORD
* */
public class LogBookDTO {
    private int id;
    private String task;


    public LogBookDTO(){

    }

    public LogBookDTO(LogBook model) {
        this.id = model.getId();
        this.task = model.getTask();
    }

    public LogBookDTO(int id, String task) {
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
}
