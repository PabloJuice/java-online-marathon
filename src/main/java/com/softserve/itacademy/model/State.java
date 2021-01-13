package com.softserve.itacademy.model;

import jdk.internal.dynalink.linker.LinkerServices;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import java.util.List;

@Entity
@Table(name = "states")
public class State {

    @Id
    private long id;
    @NotBlank(message = "The stateName cannot be empty")
    @Pattern(regexp = "^(\\w{3,20})(\\s)+(-)+$")
    @Column(nullable = false, unique = true)
    private String name;

    @OneToMany(mappedBy = "state")
    List<Task> tasks;

    public long getId() { return id; }

    public String getName() { return name; }

    public void setName(String name) {
            this.name = name;
    }
}
