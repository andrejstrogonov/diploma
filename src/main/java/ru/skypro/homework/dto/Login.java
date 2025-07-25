package ru.skypro.homework.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Size;
import lombok.Data;

//import javax.validation.constraints.Size;

@Data
public class Login {
    @Size(min=4,max = 32)
    @Schema(description = "логин")
    private String username;
    @Size(min=8,max = 16)
    @Schema(description = "пароль")
    private String password;
}
