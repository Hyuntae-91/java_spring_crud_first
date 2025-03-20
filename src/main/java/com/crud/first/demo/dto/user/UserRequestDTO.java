package com.crud.first.demo.dto.user;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public class UserRequestDTO {
    @NotBlank
    private String name;

    @NotBlank
    @Email
    private String email;

    public String getName() { return name; }

    public String getEmail() { return email; }

    public void setEmail(String email) { this.email = email; }
}
