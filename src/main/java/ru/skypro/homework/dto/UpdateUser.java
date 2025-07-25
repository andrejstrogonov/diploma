package ru.skypro.homework.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Data;



@Data
public class UpdateUser {
    @Size(min=3,max = 10)
    @Schema(description = "имя пользователя")
    private String firstName;
    @Size(min=3,max = 10)
    @Schema(description = "фамилия пользователя")
    private String lastName;
    @Pattern(regexp ="\\+7\\s?\\(?\\d{3}\\)?\\s?\\d{3}-?\\d{2}-?\\d{2}")
    @Schema(description = "телефон пользователя",example = "+7 (695) 498-51-37")
    private String phone;
}
