package com.softserve.itacademy.repository;

import com.softserve.itacademy.model.Priority;
import com.softserve.itacademy.model.Task;

import java.util.LinkedList;
import java.util.List;

public class TaskRepository {
    private List<Task> todo;

    private TaskRepository() {
        todo = new LinkedList<>();
    }

    public boolean create(Task task) {
        boolean status = todo.stream().anyMatch(t -> t.getTitle().equals(task.getTitle()));
        if (!status) {
            return todo.add(task);
        }
        return false;
    }

    public Task read(int id) {
        return todo.stream().filter(task -> task.getId() == id).findFirst().orElse(null);
    }

    public boolean update(Task task) {
        Task oldTask = read(task.getId());
        if (oldTask != null) {
            return todo.set(todo.indexOf(oldTask), task) != null;
        }
        return false;
    }

    public boolean delete(int id) {
        Task task = read(id);
        if (task != null) {
            return todo.remove(task);
        }
        return false;
    }

    public List<Task> all() {
        return todo;
    }

    private static TaskRepository taskRepository = null;

    public static TaskRepository getTaskRepository() {
        if (taskRepository == null) {
            taskRepository = new TaskRepository();
        }
        return taskRepository;
    }
}
