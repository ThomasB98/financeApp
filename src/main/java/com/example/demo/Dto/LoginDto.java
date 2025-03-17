package com.example.demo.Dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.*;


@NoArgsConstructor
@AllArgsConstructor
public class LoginDto {
    @NotBlank(message = "Username or Email is required")
    @Size(min = 4, max = 50, message = "Username or Email must be between 4 and 50 characters")
    private String usernameOrEmail;

    @NotBlank(message = "Password is required")
    @Size(min = 8, message = "Password must be at least 8 characters long")
    private String password;

    public @NotBlank(message = "Password is required") @Size(min = 8, message = "Password must be at least 8 characters long") String getPassword() {
        return password;
    }

    public void setPassword(@NotBlank(message = "Password is required") @Size(min = 8, message = "Password must be at least 8 characters long") String password) {
        this.password = password;
    }

    public @NotBlank(message = "Username or Email is required") @Size(min = 4, max = 50, message = "Username or Email must be between 4 and 50 characters") String getUsernameOrEmail() {
        return usernameOrEmail;
    }

    public void setUsernameOrEmail(@NotBlank(message = "Username or Email is required") @Size(min = 4, max = 50, message = "Username or Email must be between 4 and 50 characters") String usernameOrEmail) {
        this.usernameOrEmail = usernameOrEmail;
    }
}
