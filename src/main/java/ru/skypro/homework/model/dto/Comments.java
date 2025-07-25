package ru.skypro.homework.model.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.util.List;
@Data
public class Comments {
    @Schema(description = "общее количество комментариев")
    private int count;
    private List<Comment>results;
}
