package com.commercial.logbook_app.dto;
/*
* This class holds information about the user's RECORD
* */
public class LogBookDTO {
    private int id;
    private String task;

<<<<<<< HEAD
    public LogBookDTO(){

    }

=======
>>>>>>> b3aec1878e0f9e2007d3c4f480efa2d388d5d6c1
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
