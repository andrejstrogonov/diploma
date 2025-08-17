package ru.skypro.homework.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;
import org.springframework.stereotype.Component;
import ru.skypro.homework.dto.User;
import ru.skypro.homework.model.RegisterUserModel;
import ru.skypro.homework.model.UserModel;

@Mapper(componentModel = "spring")
@Component
public interface UserMapper {
    UserMapper INSTANCE = Mappers.getMapper(UserMapper.class);
    @Mapping(target = "userName", source = "email")
    UserModel toDto(User user );
    @Mapping(target = "email", source = "UserModel.userName")
    @Mapping(target="id",source = "UserModel.id")
    User toModel(UserModel UserModel);
}
