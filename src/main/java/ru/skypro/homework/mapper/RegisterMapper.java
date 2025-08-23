package ru.skypro.homework.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.NullValuePropertyMappingStrategy;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;
import org.springframework.stereotype.Component;
import ru.skypro.homework.dto.Register;
import ru.skypro.homework.model.UserModel;

@Component
@Mapper(componentModel = "spring",nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE,
        unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface RegisterMapper {
         RegisterMapper INSTANCE = Mappers.getMapper(RegisterMapper.class);
UserModel userToDto(Register register);
        @Mapping(target = "userName", source = "username")
        UserModel registerToDto(Register register);
       @Mapping(target = "username", source="userModel.userName")
        Register toModel(UserModel userModel);
        }
