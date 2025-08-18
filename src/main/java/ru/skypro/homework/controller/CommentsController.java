package ru.skypro.homework.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.skypro.homework.dto.CreateOrUpdateComment;
import ru.skypro.homework.dto.Comment;
import ru.skypro.homework.dto.Comments;
import ru.skypro.homework.service.CommentService;


@RestController
@RequestMapping("/ads")
//@RequiredArgsConstructor
@Tag(name = "Комментарии")
public class CommentsController {
    private final CommentService commentService;

    public CommentsController(CommentService commentService) {
        this.commentService = commentService;
    }

    @Tag(name = "Комментарии")
    @GetMapping("/{id}/comments")
    @Operation(summary = "Получение комментариев объявления")
    @ApiResponses(value = {
            @ApiResponse( description = "Ok",responseCode ="200",content = { @Content(schema =
            @Schema(implementation = Comments.class), mediaType = "application/json") }),
            @ApiResponse( description = "Unauthorized", responseCode = "401",content = { @Content(schema = @Schema()) }),
            @ApiResponse( description = "Not fount", responseCode ="404",content = { @Content(schema = @Schema()) })
    })
    public ResponseEntity<Comments> getCommentsOnAd(@RequestParam("id")int id) {
        return commentService.getCommentsOnAd(id);
    }
    @Tag(name = "Комментарии")
    @PostMapping("/{id}/comments")
    @Operation(summary = "Добавления комментария к объявлению")
    @ApiResponses(value = {
            @ApiResponse( description = "Ok",responseCode ="200",content = { @Content(schema =
            @Schema(implementation = Comment.class), mediaType = "application/json") }),
            @ApiResponse( description = "Unauthorized", responseCode = "401",content = { @Content(schema = @Schema()) }),
            @ApiResponse( description = "Not fount", responseCode ="404",content = { @Content(schema = @Schema()) })
    })
    public ResponseEntity<Comment> addCommentToAd(@RequestParam("ID продукта")int id, @RequestBody String text) {
        return commentService.addCommentToAd(id,text);
    }
     @Tag(name = "Комментарии")
    @DeleteMapping("/{adId}/comments/{commentId}")
    @Operation(summary = "Удаление комментария")
    @ApiResponses(value = {
            @ApiResponse( description = "Ok",responseCode ="200",content = { @Content(schema = @Schema()) }),
            @ApiResponse( description = "Unauthorized", responseCode = "401",content = { @Content(schema = @Schema()) }),
            @ApiResponse( description = "Forbidden", responseCode = "403",content = { @Content(schema = @Schema()) }),
            @ApiResponse( description = "Not fount", responseCode ="404",content = { @Content(schema = @Schema()) })
    })
    public ResponseEntity<Void> deleteCommentToAdId(@RequestParam("ID продукта")int adId,int commentId) {
        return commentService.deleteCommentToAdId(adId,commentId);
    }
    @Tag(name = "Комментарии")
    @PatchMapping("/{adId}/comments/{commentId}")
    @Operation(summary = "Обновление комментария")
    @ApiResponses(value = {
            @ApiResponse( description = "Ok",responseCode ="200",content = { @Content(schema =
            @Schema(implementation = Comment.class), mediaType = "application/json") }),
            @ApiResponse( description = "Unauthorized", responseCode = "401",content = { @Content(schema = @Schema()) }),
            @ApiResponse( description = "Forbidden", responseCode = "403",content = { @Content(schema = @Schema()) }),
            @ApiResponse( description = "Not fount", responseCode ="404",content = { @Content(schema = @Schema()) })
    })
    public ResponseEntity<Comment> updatingComment(@RequestParam("adId")int adId,@RequestParam("commentId")int commentId,
                                   @RequestBody CreateOrUpdateComment createOrUpdateComment) {
        return commentService.updatingComment(adId,commentId,createOrUpdateComment);
    }
   }

