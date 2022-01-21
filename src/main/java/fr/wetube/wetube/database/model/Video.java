package fr.wetube.wetube.database.model;

import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "videos")
public class Video {
    @Id
    @Column(name = "id", nullable = false)
    @Type(type = "uuid-char")
    @GeneratedValue
    private UUID id;

    @Column(nullable = false)
    private String title;

    @Column
    private String description;

    @Column(nullable = false)
    private String thumbnail;

    @Column(nullable = false)
    private String path;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private VideoType type;

    @OneToOne
    @JoinColumn(nullable = false)
    private User author;

    @Column
    private LocalDateTime created;

    @Column
    private LocalDateTime edited;

    public Video() {}

    public Video(String title, String description, String thumbnail, String path, VideoType type, User author) {
        this.title = title;
        this.description = description;
        this.thumbnail = thumbnail;
        this.path = path;
        this.type = type;
        this.author = author;
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

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getThumbnail() {
        return thumbnail;
    }

    public void setThumbnail(String thumbnail) {
        this.thumbnail = thumbnail;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public VideoType getType() {
        return type;
    }

    public void setType(VideoType type) {
        this.type = type;
    }

    public User getAuthor() {
        return author;
    }

    public void setAuthor(User author) {
        this.author = author;
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
