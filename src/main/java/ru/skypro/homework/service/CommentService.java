package ru.skypro.homework.service;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import ru.skypro.homework.dto.CreateOrUpdateComment;
import ru.skypro.homework.dto.Comment;
import ru.skypro.homework.dto.Comments;

import ru.skypro.homework.mapper.CommentMapper;
import ru.skypro.homework.mapper.CreateOrUpdateCommentMapper;
import ru.skypro.homework.model.AdModel;
import ru.skypro.homework.model.CommentModel;
import ru.skypro.homework.model.UserModel;
import ru.skypro.homework.repository.AdRepository;
import ru.skypro.homework.repository.CommentRepository;
import ru.skypro.homework.repository.UserRepository;

import java.util.ArrayList;
import java.util.List;

import static java.lang.System.currentTimeMillis;

@Service
public class CommentService {
    private final CommentRepository commentRepository;
    private final AdRepository adRepository;
    private final UserRepository userRepository;
    private final CommentMapper commentMapper;
    private final CreateOrUpdateCommentMapper createOrUpdateCommentMapper;

    public CommentService(CommentRepository commentRepository, AdRepository adRepository, UserRepository userRepository,
                          CommentMapper commentMapper, CreateOrUpdateCommentMapper createOrUpdateCommentMapper) {
        this.commentRepository = commentRepository;
        this.adRepository = adRepository;
        this.userRepository = userRepository;
        this.commentMapper = commentMapper;
        this.createOrUpdateCommentMapper = createOrUpdateCommentMapper;
    }

    public ResponseEntity<Comments> getCommentsOnAd(int id) {
        List<CommentModel> listComment = commentRepository.listCommentModel(id);
        if (listComment == null) {
            return ResponseEntity.status(404).build();
        }
        List<Comment> commentsDto = new ArrayList<>();
        int author = adRepository.findAuthorToPk(id);
        UserModel userModel = userRepository.userModelFindId(author);
        for (CommentModel variable : listComment) {
            commentsDto.add(commentMapper.toModel(variable, userModel));
        }

        return ResponseEntity.ok(commentMapper.adsToComments(commentsDto));
    }

    public ResponseEntity<Comment> addCommentToAd(int id, String text) {
        AdModel adModel = adRepository.findPkObject(id);
        if (adModel == null) {
            return ResponseEntity.status(403).build();
        }
        long time = currentTimeMillis();
        int author = adRepository.findAuthorToPk(id);
        UserModel userModel = userRepository.userModelFindId(author);
        commentRepository.saveComment(id, time, text);
        return ResponseEntity.ok(commentMapper.toModel(commentRepository.commentModel(id), userModel));
    }

    public ResponseEntity<Void> deleteCommentToAdId(int adId, int commentId) {
        if (commentRepository.commentModel(commentId) == null) {
            return ResponseEntity.status(404).build();
        }
        if (commentRepository.findPkToAdPk(adId) == null) {
            return ResponseEntity.status(403).build();
        }
        commentRepository.deleteLine(commentId);
        return ResponseEntity.ok().build();
    }

    public ResponseEntity<Comment> updatingComment(int adId, int commentId, CreateOrUpdateComment createOrUpdateComment) {
        if (commentRepository.commentModel(commentId) == null) {
            return ResponseEntity.status(404).build();
        }
        if (commentRepository.findPkToAdPk(adId) == null) {
            return ResponseEntity.status(403).build();
        }
        int author = adRepository.findAuthorToPk(adId);
        UserModel userModel = userRepository.userModelFindId(author);
        long time = currentTimeMillis();
        commentRepository.updatingComment(createOrUpdateCommentMapper.toDto(createOrUpdateComment).getText(), time, commentId);
        CommentModel commentModel = commentRepository.commentModel(commentId);
        return ResponseEntity.ok(commentMapper.toModel(commentModel, userModel));
    }
}
