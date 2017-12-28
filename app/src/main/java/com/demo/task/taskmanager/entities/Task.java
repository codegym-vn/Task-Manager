package com.demo.task.taskmanager.entities;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by Quy Duc on 12/20/2017.
 */

public class Task implements Serializable {
    private String TaskID;
    private String Title;
    private String Description;
    private boolean IsDone;
    private Date Deadline;

    public Task() {
    }

    public String getTaskID() {
        return TaskID;
    }

    public void setTaskID(String taskID) {
        TaskID = taskID;
    }

    public String getTitle() {
        return Title;
    }

    public void setTitle(String title) {
        Title = title;
    }

    public String getDescription() {
        return Description;
    }

    public void setDescription(String description) {
        Description = description;
    }

    public boolean isDone() {
        return IsDone;
    }

    public void setDone(boolean done) {
        IsDone = done;
    }

    public Date getDeadline() {
        return Deadline;
    }

    public void setDeadline(Date deadline) {
        Deadline = deadline;
    }
}
