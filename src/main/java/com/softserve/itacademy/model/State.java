package com.softserve.itacademy.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

@Entity
@Table(name = "states")
public class State {

    @Id
    private long id;
    @NotBlank(message = "The stateName cannot be empty")
    @Pattern(regexp = "^(\\w{3,20})(\\s)+(-)+$")
    @Column(nullable = false, unique = true)
    private String name;

    public long getId() { return id; }

    public String getName() { return name; }

    public void setName(String name) {
            this.name = name;
    }

    @Override
    public String toString() {
        return "State {" +
                "id = " + id +
                ", name = '" + name + '\'' +
                "} ";
    }
}
