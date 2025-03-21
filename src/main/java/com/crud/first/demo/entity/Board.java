package com.crud.first.demo.entity;

import jakarta.persistence.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;

@Entity
public class Board {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String title;
    private String username;
    private String content;
    private String passwordHash;
    private String passwordSalt;

    @Column(columnDefinition = "TINYINT")
    private Byte status = 1;

    @CreationTimestamp
    private LocalDateTime createdAt;

    @UpdateTimestamp
    private LocalDateTime updatedAt;

    public Board() {}

    public Board(String title, String username, String content, String passwordHash, String passwordSalt) {
        this.title = title;
        this.username = username;
        this.content = content;
        this.passwordHash = passwordHash;
        this.passwordSalt = passwordSalt;
    }

    public Integer getId() { return id; }

    public String getTitle() { return title; }

    public void setTitle(String title) { this.title = title; }

    public String getUsername() { return username; }

    public String getContent() { return content; }

    public void setContent(String content) { this.content = content; }

    public String getPasswordHash() { return passwordHash; }

    public String getPasswordSalt() { return passwordSalt; }

    public void setStatus(Byte status) {
        this.status = status;
    }
}
