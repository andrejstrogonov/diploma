package ru.skypro.homework.service;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import ru.skypro.homework.dto.Ad;
import ru.skypro.homework.dto.Ads;
import ru.skypro.homework.dto.CreateOrUpdateAd;
import ru.skypro.homework.dto.ExtendedAd;
import ru.skypro.homework.mapper.*;
import ru.skypro.homework.model.AdModel;
import ru.skypro.homework.model.AvatarAdModel;
import ru.skypro.homework.model.UserModel;
import ru.skypro.homework.repository.AdRepository;
import ru.skypro.homework.repository.AvatarAdRepository;
import ru.skypro.homework.repository.UserRepository;
import ru.skypro.homework.service.impl.AuthServiceImpl;

import java.io.IOException;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

@Service
public class AdService {
    private final AdRepository adRepository;
    private final UserRepository userRepository;
    private final AuthServiceImpl authService;
    private final AvatarAdRepository avatarAdRepository;
    private final AvatarComponent avatarComponent;
    private final AdMapper adMapper;
    private final CreateOrUpdateAdMapper createOrUpdateAdMapper;
    private final ExtendedAdMapper extendedMapper;

    public AdService(AdRepository adRepository, UserRepository userRepository, AuthServiceImpl authService,
                     AvatarAdRepository avatarAdRepository, AvatarComponent avatarComponent, AdMapper adMapper,
                     CreateOrUpdateAdMapper createOrUpdateAdMapper, ExtendedAdMapper extendedMapper) {
        this.adRepository = adRepository;
        this.userRepository = userRepository;
        this.authService = authService;
        this.avatarAdRepository = avatarAdRepository;
        this.avatarComponent = avatarComponent;

        this.adMapper = adMapper;
        this.createOrUpdateAdMapper = createOrUpdateAdMapper;
        this.extendedMapper = extendedMapper;
    }


    public Ads getAllService() {
        UserModel userModel = userRepository.informationAboutUserName(authService.usernameAuthorised(), authService.userRoleAuthorised());
        List<AdModel> adList = adRepository.findAllAd();
        List<UserModel> listModel = new ArrayList<>();
        List<Ad> ad = new ArrayList<>();
        for (AdModel variable : adList) {
            listModel.add(userRepository.userModelFindId(adRepository.findAuthorToPk(variable.getPk())));
            ad.add(adMapper.toModel(variable, userModel));
        }
        return adMapper.adsToAd(ad);
    }


    public ExtendedAd getInformationAboutAd(int id) {
        AdModel adModel = adRepository.findPkObject(id);
        System.err.println(adModel);
        UserModel userModel = userRepository.userModelFindId(adRepository.author(id));
        return extendedMapper.toDto(adModel, userModel);
    }

    public ResponseEntity<Void> deleteAd(int id) throws IOException {
        String filePath = adRepository.findPkImageAvatar(id);
        if (filePath != null) {
            avatarComponent.delete(filePath);
        }
        avatarAdRepository.deleteLine(filePath);
        adRepository.deleteAd(id);

        return ResponseEntity.ok().build();
    }

    public Ad updatingInformationAboutAd(int id, CreateOrUpdateAd createOrUpdateAd) {
        AdModel revers = createOrUpdateAdMapper.toDto(createOrUpdateAd);
        System.err.println(revers);
        adRepository.updatingUserInformationAd(revers.getTitle(),
                revers.getDescription(), revers.getPrice(), id);
        UserModel userModel = userRepository.userModelFindId(id);
        AdModel adModelAbout = adRepository.findPkObject(id);
        return adMapper.toModel(adModelAbout, userModel);
    }

    public Ads getAdsFromAuthorized() {
        UserModel userModel = userRepository.informationAboutUserName(authService.usernameAuthorised(), authService.userRoleAuthorised());
        List<AdModel> adList = adRepository.findListPkAdModel(userModel.getId());
        List<Ad> ad = new ArrayList<>();
        for (AdModel variable : adList) {
            ad.add(adMapper.toModel(variable, userModel));
        }
        return adMapper.adsToAd(ad);
    }

    public ResponseEntity<String> UpdatingAdImage(int id, MultipartFile image) throws IOException {
        AdModel adModel = adRepository.findPkObject(id);
        String name = adModel.getImage().replace("avatar/ad", "").trim();
        Path filePath = avatarComponent.saveAvatar("avatar/ad", name.substring(0, name.indexOf(".")), image);
        adRepository.updateImage(filePath.toString(), adModel.getPk());
        AvatarAdModel avatar = new AvatarAdModel();
        avatar.setFilePath(filePath.toString());
        avatar.setFileSize(image.getSize());
        avatar.setMediaType(image.getContentType());
        avatar.setData(avatarComponent.generateImagePreview(filePath));
        avatarAdRepository.updateAvatar(avatar.getFilePath(), avatar.getMediaType(), avatar.getFileSize(), avatar.getData(), id);
        return ResponseEntity.status(200).build();
    }

    public ResponseEntity<Ad> addingAd(CreateOrUpdateAd createOrUpdateAd, MultipartFile image) throws IOException {
        AdModel revers = createOrUpdateAdMapper.toDto(createOrUpdateAd);
        if (image.getSize() >= 1024 * 300) {
            return ResponseEntity.status(403).build();
        }
        UserModel userModel = userRepository.informationAboutUserName(authService.usernameAuthorised(), authService.userRoleAuthorised());
        AdModel advertisementAd = new AdModel();
        advertisementAd.setTitle(revers.getTitle());
        advertisementAd.setPrice(revers.getPrice());
        advertisementAd.setDescription(revers.getDescription());
        Path filePath = avatarComponent.saveAvatar("avatar/ad", advertisementAd.getTitle() +
                " (avatar N " + (avatarAdRepository.countAvatar() + 1) + ")", image);
        AvatarAdModel avatar = new AvatarAdModel();
        avatar.setFilePath(filePath.toString());
        avatar.setFileSize(image.getSize());
        avatar.setMediaType(image.getContentType());
        avatar.setData(avatarComponent.generateImagePreview(filePath));
        adRepository.saveAd(userModel.getId(), advertisementAd.getTitle(),
                advertisementAd.getDescription(), advertisementAd.getPrice());
        int pk = adRepository.findPk(userModel.getId(), advertisementAd.getTitle(),
                advertisementAd.getDescription(), advertisementAd.getPrice());
        avatarAdRepository.save(pk, avatar.getFilePath(), avatar.getFileSize(), avatar.getMediaType(), avatar.getData());
        adRepository.updateImage(avatar.getFilePath(), pk);
        AdModel out = adRepository.findPkObject(pk);
        return ResponseEntity.status(201).body(adMapper.toModel(out, userModel));
    }
}
