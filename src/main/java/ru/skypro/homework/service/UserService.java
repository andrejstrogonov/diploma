package ru.skypro.homework.service;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import ru.skypro.homework.dto.NewPassword;
import ru.skypro.homework.dto.UpdateUser;
import ru.skypro.homework.dto.User;
import ru.skypro.homework.mapper.NewPasswordMapper;
import ru.skypro.homework.mapper.UpdateUserMapper;
import ru.skypro.homework.mapper.UserMapper;
import ru.skypro.homework.model.AvatarUserModel;
import ru.skypro.homework.model.UserModel;
import ru.skypro.homework.repository.AvatarUserRepository;
import ru.skypro.homework.repository.UserRepository;
import ru.skypro.homework.service.impl.AuthServiceImpl;

import java.io.*;
import java.nio.file.Path;

@Service
public class UserService {
    private final UserRepository userRepository;
    private final AuthServiceImpl authService;
    private final AvatarUserRepository avatarUserRepository;
    private final AvatarComponent avatarComponent;
    private final UserMapper userMapper;
    private final UpdateUserMapper updateUserMapper;
    private final NewPasswordMapper newPasswordMapper;

    public UserService(UserRepository userRepository, AuthServiceImpl authService,
                       AvatarUserRepository avatarUserRepository, AvatarComponent avatarComponent,
                       UserMapper userMapper, UpdateUserMapper updateUserMapper, NewPasswordMapper newPasswordMapper) {
        this.userRepository = userRepository;
        this.authService = authService;
        this.avatarUserRepository = avatarUserRepository;
        this.avatarComponent = avatarComponent;
        this.userMapper = userMapper;
        this.updateUserMapper = updateUserMapper;
        this.newPasswordMapper = newPasswordMapper;
    }

    public ResponseEntity<Void> passwordUpdates(NewPassword newPassword) {
        UserModel reversNewPasswordMapper = newPasswordMapper.toDto(newPassword);
        UserModel registerUser = userRepository.findIdPassword(reversNewPasswordMapper.getPassword().trim());
        if (registerUser == null) {
            return ResponseEntity.status(403).build();
        }
        userRepository.updatePassword(reversNewPasswordMapper.getNewPassword(), authService.usernameAuthorised());
        return ResponseEntity.ok().build();
    }

    public User getInformationAboutUser() {
        UserModel userNameInformation = userRepository.informationAboutUserName(authService.usernameAuthorised(),
                authService.userRoleAuthorised());
        return userMapper.toModel(userNameInformation);
    }

    public UpdateUser updatingUserInformation(UpdateUser updateUser) {
        UserModel reverseUpdateUser = updateUserMapper.toDto(updateUser);
        userRepository.updatingUserInformationAuthorised(reverseUpdateUser.getFirstName(), reverseUpdateUser.getLastName(),
                reverseUpdateUser.getPhone(), userRepository.informationAboutUserName(authService.usernameAuthorised(),
                        authService.userRoleAuthorised()).getId());
        return updateUserMapper.toModel(userRepository.informationAboutUserName(authService.usernameAuthorised(),
                authService.userRoleAuthorised()));
    }

    public ResponseEntity<Void> updatingUsersAvatar(MultipartFile image) throws IOException {
        if (image.getSize() >= 1024 * 300) {
            return ResponseEntity.status(403).build();
        }
        UserModel userModel = userRepository.informationAboutUserName(authService.usernameAuthorised(),
                authService.userRoleAuthorised().replace("ROLE_", ""));
        Path filePath = avatarComponent.saveAvatar("avatar/user", userModel.getId().toString(), image);
        Integer avatarUserModel = avatarUserRepository.findUserAvatar(userModel.getId());
        if (avatarUserModel != null) {
            avatarUserRepository.deleteLine(userModel.getId());
        }
        AvatarUserModel avatar = new AvatarUserModel();
        avatar.setFilePath(filePath.toString());
        avatar.setFileSize(image.getSize());
        avatar.setMediaType(image.getContentType());
        avatar.setData(avatarComponent.generateImagePreview(filePath));
        avatarUserRepository.save(avatar.getFilePath(), avatar.getFileSize(), avatar.getMediaType(),
                avatar.getData(), userModel.getId());
        userRepository.updateImage(filePath.toString(), userModel.getId());
        return ResponseEntity.status(200).build();
    }
}


