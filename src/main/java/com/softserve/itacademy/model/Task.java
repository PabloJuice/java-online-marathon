package com.softserve.itacademy.model;

import javax.persistence.*;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Entity
@Table(name = "tasks")
public class Task {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @NotBlank
    @Min(3)
    @Max(200)
    @Column(name = "name", nullable = false)
    private String name;

    @NotBlank
    @Column(name = "priority", nullable = false)
    private Priority priority;

    private long stateId;
    private long todoId;

    public long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Priority getPriority() {
        return priority;
    }

    public void setPriority(Priority priority) {
        this.priority = priority;
    }

    public long getStateId() {
        return stateId;
    }

    public long getTodoId() {
        return todoId;
    }

    @Override
    public String toString() {
        return "Task{" +
                "name='" + name + '\'' +
                ", priority=" + priority +
                ", stateId=" + stateId +
                '}';
    }
}
