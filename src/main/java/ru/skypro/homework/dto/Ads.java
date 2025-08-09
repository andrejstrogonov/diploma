package ru.skypro.homework.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.util.List;
@Data
public class Ads {
    @Schema(description = "общее количество объявлений")
    private int count;
    private List<Ad>results;
public Ads(){}

    public Ads(int count, List<Ad> results) {
        this.count = count;
        this.results = results;
    }
}
