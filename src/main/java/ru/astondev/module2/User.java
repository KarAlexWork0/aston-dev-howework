package ru.astondev.module2;
import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "users") // Maps to the 'users' table in the database
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // Auto-generates ID
    private Long id;

    @Column(name = "username", unique = true, nullable = false)
    private String username;

    @Column(name = "age", nullable = false)
    private int age;

    @Column(name = "email", nullable = false)
    private String email;

    @Column(name = "created_at", nullable = false, updatable = false)
    private LocalDateTime createdAt;

    public User() {}

    public User(String username, String email, int age) {
        this.username = username;
        this.email = email;
        this.age = age;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    @PrePersist
    protected void onCreate() {
        createdAt = LocalDateTime.now();
    }
    //only getter
    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    @Override
    public String toString() {
        return "id=" + id + ", username=" + username + ", email=" + email + ", created at=" + createdAt;
    }
}
