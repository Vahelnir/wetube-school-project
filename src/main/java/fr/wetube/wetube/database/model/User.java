package fr.wetube.wetube.database.model;

import com.sun.istack.Nullable;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "users")
public class User {
    @Id
    @Column(name = "id", nullable = false)
    @Type(type = "uuid-char")
    @GeneratedValue
    private UUID id;

    @Column(unique = true, nullable = false)
    private String email;

    @Column(nullable = false)
    private String username;

    @Column(nullable = false)
    private String password;

    @Column
    private LocalDateTime created;

    @Column
    @Nullable
    private LocalDateTime edited;

    public User() {}

    public User(String email, String password) {
        this(email, password, LocalDateTime.now(), null);
    }

    public User(String email, String password, LocalDateTime created, LocalDateTime edited) {
        this.email = email;
        this.password = password;
        this.created = created;
        this.edited = edited;
    }

    @PrePersist
    void createdAt() {
        this.created = LocalDateTime.now();
        updatedAt();
    }

    @PreUpdate
    void updatedAt() {
        this.edited = LocalDateTime.now();
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public LocalDateTime getCreated() {
        return created;
    }

    public void setCreated(LocalDateTime created) {
        this.created = created;
    }

    public LocalDateTime getEdited() {
        return edited;
    }

    public void setEdited(LocalDateTime edited) {
        this.edited = edited;
    }

}
