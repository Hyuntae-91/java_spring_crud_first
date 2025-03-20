package com.crud.first.demo.dto.board;

import jakarta.validation.constraints.NotBlank;

public class BoardCreateRequestDTO {
    @NotBlank
    private String title;

    @NotBlank
    private String content;

    @NotBlank
    private String username;

    @NotBlank
    private String password;

    public String getTitle() { return title; }

    public String getContent() { return content; }

    public String getUsername() { return username; }

    public String getPassword() { return password; }

}
