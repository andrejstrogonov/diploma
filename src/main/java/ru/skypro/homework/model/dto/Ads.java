package ru.skypro.homework.model.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.util.List;
@Data
public class Ads {
    @Schema(description = "общее количество объявлений")
    private int count;
    private List<Ad>results;
}
