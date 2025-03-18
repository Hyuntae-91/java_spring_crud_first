package com.crud.first.demo.dto.user;

import com.crud.first.demo.entity.User;

public class UserResponseDTO {
    private Long id;
    private String name;
    private String email;
    private String createdAt;

    public UserResponseDTO(User user) {
        this.id = user.getId();
        this.name = user.getName();
        this.email = user.getEmail();
        this.createdAt = user.getCreatedAt().toString();
    }

    public Long getId() { return id; }

    public String getName() { return name; }

    public String getEmail() { return email; }

    public String getCreatedAt() { return createdAt; }
}
