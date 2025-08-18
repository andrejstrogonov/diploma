package ru.skypro.homework.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;
import org.springframework.stereotype.Component;
import ru.skypro.homework.dto.NewPassword;
import ru.skypro.homework.model.RegisterUserModel;
import ru.skypro.homework.model.UserModel;

@Component
@Mapper(componentModel = "spring",unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface NewPasswordMapper {
        NewPasswordMapper INSTANCE = Mappers.getMapper(NewPasswordMapper.class);
         @Mapping(target = "password", source = "currentPassword")
         UserModel toDto(NewPassword newPassword );
    @Mapping(target = "currentPassword", source = "password")
        NewPassword toModel(UserModel userModel);
           }
