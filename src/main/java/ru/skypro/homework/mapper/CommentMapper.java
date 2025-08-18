package ru.skypro.homework.mapper;


import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;
import org.springframework.stereotype.Component;
import ru.skypro.homework.dto.Ad;
import ru.skypro.homework.dto.Ads;
import ru.skypro.homework.dto.Comment;
import ru.skypro.homework.dto.Comments;
import ru.skypro.homework.model.AdModel;
import ru.skypro.homework.model.CommentModel;
import ru.skypro.homework.model.RegisterUserModel;
import ru.skypro.homework.model.UserModel;

import java.util.List;

@Component
@Mapper(componentModel = "spring",uses = {CommentMapper.class,AdMapper.class,UserMapper.class})
public interface CommentMapper {
    CommentMapper INSTANCE = Mappers.getMapper(CommentMapper.class);
          CommentModel toDto(Comment comment);
    @Mapping(target="author",source="userModel.id")
    @Mapping(target="authorImage",source="userModel.image")
    @Mapping(target="authorFirstName",source="userModel.firstName")
    Comment toModel(CommentModel commentModel, UserModel userModel);
    default Comments adsToComments(List<Comment> comments){
        Comments comments1=new Comments();
        comments1.setCount(comments.size());
        comments1.setResults(comments);
        return comments1;
    }
}


