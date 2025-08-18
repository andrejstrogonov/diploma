package ru.skypro.homework.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.NullValuePropertyMappingStrategy;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;
import org.springframework.stereotype.Component;
import ru.skypro.homework.dto.ExtendedAd;
import ru.skypro.homework.model.AdModel;
import ru.skypro.homework.model.RegisterUserModel;
import ru.skypro.homework.model.UserModel;

@Component
@Mapper(componentModel="spring",nullValuePropertyMappingStrategy= NullValuePropertyMappingStrategy.IGNORE,unmappedTargetPolicy= ReportingPolicy.IGNORE)
public interface ExtendedAdMapper{
    ExtendedAdMapper INTERFACE= Mappers.getMapper(ExtendedAdMapper.class);
    @Mapping(target="pk",source="adModel.pk")
     @Mapping(target="authorFirstName",source="userModel.firstName")
     @Mapping(target="authorLastName",source="userModel.lastName")
    @Mapping(target="email",source="userModel.userName")
    @Mapping(target="image",source="adModel.image")
    ExtendedAd toDto(AdModel adModel, UserModel userModel);
}
