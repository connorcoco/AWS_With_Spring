package com.example.awswithspring.dto.AuthDTO;

import com.example.awswithspring.validation.annotation.GenderValid;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.*;

public class AuthRequestDTO {

    @Getter
    public static class JoinDTO{

        @NotEmpty
        @Email(message = "Invalid email format")
        private String username;

        @NotEmpty
        private String password;

        @NotEmpty
        private String nickname;

        @NotNull
        @GenderValid
        private String gender;
    }

    @Getter
    public static class LoginDTO{

        @NotEmpty
        @Email(message = "Invalid email format")
        private String username;

        @NotEmpty
        private String password;
    }
}