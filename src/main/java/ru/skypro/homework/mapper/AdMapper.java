package ru.skypro.homework.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;
import org.springframework.stereotype.Component;
import ru.skypro.homework.dto.Ad;
import ru.skypro.homework.dto.Ads;
import ru.skypro.homework.model.AdModel;
import ru.skypro.homework.model.UserModel;

import java.util.List;

@Component
@Mapper(componentModel = "spring",unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface AdMapper {
    AdMapper INSTANCE = Mappers.getMapper(AdMapper.class);
    AdModel toDto(Ad ad );
    @Mapping(target="author",source="userModel.id")
    @Mapping(target="image",source="adModel.image")
    Ad toModel(AdModel adModel, UserModel userModel);
    default Ads adsToAd(List<Ad> ad){
        Ads ads=new Ads();
        ads.setCount(ad.size());
        ads.setResults(ad);
        return ads;
    }
}
