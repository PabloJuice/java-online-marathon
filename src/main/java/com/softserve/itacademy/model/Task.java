package com.softserve.itacademy.model;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Entity
@Table(name = "tasks")
public class Task {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @NotBlank
    @Size(min = 3, max = 200)
    @Column(name = "name", nullable = false)
    private String name;

    @NotBlank
    @Column(name = "priority")
    private String priority;

    @ManyToOne
    @JoinColumn(name = "state_id")
    State state;

    @ManyToOne
    @JoinColumn(name = "todo_id")
    ToDo todo;

    public long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPriority() {
        return priority;
    }

    public void setPriority(Priority priority) {
        this.priority = priority.toString();
    }

    @Override
    public String toString() {
        return "Task{" +
                "name='" + name + '\'' +
                ", priority=" + priority +
                '}';
    }
}
