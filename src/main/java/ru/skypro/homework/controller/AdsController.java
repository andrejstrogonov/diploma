package ru.skypro.homework.controller;


import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import ru.skypro.homework.dto.CreateOrUpdateAd;
import ru.skypro.homework.dto.Ad;
import ru.skypro.homework.dto.Ads;
import ru.skypro.homework.dto.ExtendedAd;

import ru.skypro.homework.model.AdModel;
import ru.skypro.homework.service.AdService;

import java.io.IOException;
import java.util.Optional;

@RestController
@RequestMapping("/ads")
//@RequiredArgsConstructor
@Tag(name = "Объявления")
public class AdsController {
    private final AdService adService;

    public AdsController(AdService adService) {
        this.adService = adService;
    }
    //@Autowired
    //CreateOrUpdateAdMapper createOrUpdateAdMapper;

    @Tag(name = "Объявления")
    @GetMapping()
    @Operation(summary = "Получение всех объявлений")
    @ApiResponse(description = "Ok", responseCode = "200", content =
            {@Content(schema = @Schema(implementation = Ads.class), mediaType = "application/json")})
    public Optional<Ads> getAllAds() {
        return Optional.of(adService.getAllService());
    }

    @Tag(name = "Объявления")
    @GetMapping("/{id}")
    @Operation(summary = "Получение информации об объявлении")
    @ApiResponses(value = {
            @ApiResponse(description = "Ok", responseCode = "200", content =
                    {@Content(schema = @Schema(implementation = ExtendedAd.class), mediaType = "application/json")}),
            @ApiResponse(description = "Unauthorized", responseCode = "401", content = {@Content(schema = @Schema())}),
            @ApiResponse(description = "Not fount", responseCode = "404", content = {@Content(schema = @Schema())})
    })
    public ExtendedAd getInformationAboutAd(@RequestParam int id) {
        return adService.getInformationAboutAd(id);

    }

    @Tag(name = "Объявления")
    @DeleteMapping("/{id}")
    @Operation(summary = "Удаление объявления")
    @ApiResponses(value = {
            @ApiResponse(description = "No content", responseCode = "204", content = {@Content(schema = @Schema())}),
            @ApiResponse(description = "Unauthorized", responseCode = "401", content = {@Content(schema = @Schema())}),
            @ApiResponse(description = "Forbidden", responseCode = "403", content = {@Content(schema = @Schema())}),
            @ApiResponse(description = "Not fount", responseCode = "404", content = {@Content(schema = @Schema())})
    })
    public ResponseEntity<Void> deleteAd(@RequestParam("ID продукта") int id) throws IOException {
        return adService.deleteAd(id);
    }

    @Tag(name = "Объявления")
    @PatchMapping("/{id}")
    @Operation(summary = "Обновление информации об объявлении")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "Ok", description = "200", content =
                    {@Content(schema = @Schema(implementation = Ad.class), mediaType = "application/json")}),
            @ApiResponse(responseCode = "Unauthorized", description = "401", content = {@Content(schema = @Schema())}),
            @ApiResponse(responseCode = "Forbidden", description = "403", content = {@Content(schema = @Schema())}),
            @ApiResponse(responseCode = "Not fount", description = "404", content = {@Content(schema = @Schema())})
    })
    public Ad updatingInformationAboutAd(@RequestParam("id") int id, @RequestBody CreateOrUpdateAd createOrUpdateAd) {
//AdModel create=createOrUpdateAdMapper.toDto(createOrUpdateAd);
        return adService.updatingInformationAboutAd(id, createOrUpdateAd);
       // return adService.updatingInformationAboutAd(id, createOrUpdateAd);
    }

    @Tag(name = "Объявления")
    @GetMapping("/me")
    @Operation(summary = "Получение объявлений авторизованного пользователя")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "Ok", description = "200", content =
                    {@Content(schema = @Schema(implementation = Ads.class), mediaType = "application/json")}),
            @ApiResponse(responseCode = "Unauthorized", description = "401", content = {@Content(schema = @Schema())})
    })
    Ads getAdsFromAuthorized() {
        return adService.getAdsFromAuthorized();
    }

    @Tag(name = "Объявления")
    @PatchMapping(value = "/{id}/image", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    @Operation(summary = "Обновление картинки объявления")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "Ok", description = "200", content =
                    {@Content(schema = @Schema(implementation = String.class), mediaType = "application/octet-stream")}),
            @ApiResponse(responseCode = "Unauthorized", description = "401", content = {@Content(schema = @Schema())}),
            @ApiResponse(responseCode = "Forbidden", description = "403", content = {@Content(schema = @Schema())}),
            @ApiResponse(responseCode = "Not fount", description = "404", content = {@Content(schema = @Schema())})
    })
    public ResponseEntity<String> UpdatingAdImage(@RequestParam("id") int id, @RequestParam MultipartFile image) throws IOException {
              return adService.UpdatingAdImage(id, image);
    }

    @Tag(name = "Объявления")
    @PostMapping(params = "parameters", consumes ={MediaType.MULTIPART_FORM_DATA_VALUE})
    @Operation(summary = "Добавление объявления")
   @ApiResponses(value = {
           @ApiResponse(responseCode = "Created", description = "201",
                  content ={@Content(schema = @Schema(implementation = Ad.class), mediaType = "application/json")}),
           @ApiResponse(responseCode = "Unauthorized", description = "401", content = {@Content(schema = @Schema())})
    })
       public ResponseEntity<Ad> addingAd(@RequestPart("parameters") CreateOrUpdateAd parameters,
                                          @RequestParam("image") MultipartFile image) throws IOException {
       // AdModel create=createOrUpdateAdMapper.toDto(parameters);
                        return adService.addingAd( parameters, image);

    }
}

