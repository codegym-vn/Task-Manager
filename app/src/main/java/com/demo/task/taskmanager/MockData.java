package com.demo.task.taskmanager;

import com.demo.task.taskmanager.entities.Task;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by Quy Duc on 12/21/2017.
 */

public class MockData {
    private static List<Task> tasks = new ArrayList<>();

    public static List<Task> getListTask(Date currentDate) {
        List<Task> tasksByDate = new ArrayList<>();
        for (Task task : tasks) {
            if (Utils.convertDateToStringNoTime(task.getDeadline()).equalsIgnoreCase(Utils.convertDateToStringNoTime(currentDate))) {
                tasksByDate.add(task);
            }
        }
        return tasksByDate;
    }

    public static List<Task> getTasks() {
        return tasks;
    }

    public static boolean removeTaskByID(String taskID) {
        for (Task task : tasks) {
            if (task.getTaskID().equalsIgnoreCase(taskID)){
                tasks.remove(task);
                return true;
            }
        }
        return false;
    }

    public static boolean update(Task task) {
        for (Task t : tasks) {
            if (t.getTaskID().equalsIgnoreCase(task.getTaskID())){
                t.setTitle(task.getTitle());
                t.setDescription(task.getDescription());
                return true;
            }
        }
        return false;
    }
}
