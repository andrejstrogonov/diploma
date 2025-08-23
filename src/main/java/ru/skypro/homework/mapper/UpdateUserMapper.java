package ru.skypro.homework.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;
import org.springframework.stereotype.Component;
import ru.skypro.homework.dto.UpdateUser;
import ru.skypro.homework.model.UserModel;

@Mapper(componentModel = "spring",unmappedTargetPolicy = ReportingPolicy.IGNORE)
@Component
public interface UpdateUserMapper {
    UpdateUserMapper INSTANCE = Mappers.getMapper(UpdateUserMapper.class);

    UserModel toDto(UpdateUser updateUser);

    UpdateUser toModel(UserModel userModel);
}
