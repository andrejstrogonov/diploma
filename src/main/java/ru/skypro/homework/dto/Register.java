package ru.skypro.homework.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Data;



@Data
public class Register {
    @Size(min=4,max = 32)
    @Schema(description = "логин")
    private String username;
    @Size(min=8,max = 16)
    @Schema(description = "пароль")
    private String password;
    @Size(min=2,max = 16)
    @Schema(description = "имя пользователя")
    private String firstName;
    @Size(min=2,max = 16)
    @Schema(description = "фамилия пользователя")
    private String lastName;
    @Pattern(regexp ="\\+7\\s?\\(?\\d{3}\\)?\\s?\\d{3}-?\\d{2}-?\\d{2}")
    @Schema(description = "телефон пользователя",example = "+7(297)98339-39")
    private String phone;
    @Schema(description = "роль пользователя")
    private Role role;
}
