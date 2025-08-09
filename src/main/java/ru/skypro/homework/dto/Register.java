package ru.skypro.homework.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.validation.constraints.Pattern;
import lombok.Data;


@Data
public class Register {
    @Schema(description = "логин",minLength = 4,maxLength = 32)
    private String username;
    @Schema(description = "пароль",minLength = 8,maxLength = 16)
    private String password;
    @Schema(description = "имя пользователя",minLength = 2,maxLength = 16)
    private String firstName;
    @Schema(description = "фамилия пользователя",minLength = 2,maxLength = 16)
    private String lastName;
    @Pattern(regexp ="\\+7\\s?\\(?\\d{3}\\)?\\s?\\d{3}-?\\d{2}-?\\d{2}")
    @Schema(description = "телефон пользователя",example = "+7(297)98339-39")
    private String phone;
    @Schema(description = "роль пользователя")
    private Role role;
}
