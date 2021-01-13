package com.softserve.itacademy.model;

import net.bytebuddy.dynamic.loading.InjectionClassLoader;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import javax.validation.constraints.*;
import java.math.BigInteger;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "users")
public class User  {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @NotBlank
    @Pattern(regexp = "^([-]?[A-Z][a-z]+)+$")
    @Column(name = "first_name", nullable = false)
    private String firstName;

    @NotBlank
    @Pattern(regexp = "^([-]?[A-Z][a-z]+)+$")
    @Column(name = "last_name", nullable = false)
    private String lastName;

    @NotBlank
    @Email
    @Column(name = "email", nullable = false, unique = true)
    private String email;

    @NotBlank
    @Pattern(regexp = "^(?=.*[A-Za-z])(?=.*\\d)(?=.*[@$!%*?&])[A-Za-z\\d@$!%*?&]{8,}$")
    @Column(name = "password", nullable = false)
    private String password;

    @ManyToOne
    @JoinColumn(name = "role_id")
    private Role role;

    @OneToMany(mappedBy = "owner")
    private List<ToDo> todos;

    @ManyToMany
    @JoinTable(name = "todo_collaborator",
            joinColumns = {
                    @JoinColumn(name = "collaborator_id", referencedColumnName = "id",
                            nullable = false)},
            inverseJoinColumns = {
                    @JoinColumn(name = "todo_id", referencedColumnName = "id",
                            nullable = false)})
    private List<ToDo> collaborations;

    public User() {
    }

    public long getId() {
        return id;
    }

    public List<ToDo> getTodos() {
        return todos;
    }

    public void setTodos(List<ToDo> todos) {
        this.todos = todos;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", role=" + role +
                '}';
    }
}
