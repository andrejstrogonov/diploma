package ru.skypro.homework.service.impl;

import org.hibernate.tool.schema.internal.exec.GenerationTargetToStdout;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import ru.skypro.homework.dto.Register;
import ru.skypro.homework.mapper.RegisterMapper;
import ru.skypro.homework.model.UserModel;
import ru.skypro.homework.repository.UserRepository;
import ru.skypro.homework.service.AuthService;


@Service
public class AuthServiceImpl implements AuthService {

    private final MyUserDetailService manager;
    private final PasswordEncoder encoder;

    public AuthServiceImpl(MyUserDetailService manager, PasswordEncoder encoder) {
        this.manager = manager;
        this.encoder = encoder;
    }
@Autowired
    UserRepository userRepository;
    @Autowired
    RegisterMapper registerMapper;


    @Override
    public boolean login(String userName, String password) {
                if (!manager.userExists(userName)) {
            return false;
        }

        UserDetails userDetails = manager.loadUserByUsername(userName);
        return encoder.matches(password, userDetails.getPassword());
        }

    @Override
    public boolean register(Register register) {
        UserModel reversRegister = registerMapper.registerToDto(register);
        if (manager.userExists(reversRegister.getUserName())) {
            return false;
        }
        manager.createUser(
                User.builder()
                        .passwordEncoder(this.encoder::encode)
                        .password(reversRegister.getPassword())
                        .username(reversRegister.getUserName())
                        .roles(reversRegister.getRole().name())
                        .build());
        System.err.println(userRoleAuthorised());
                         return true;
    }

    public String usernameAuthorised() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication != null) {
            UserDetails userDetails = (UserDetails) authentication.getPrincipal();
           return userDetails.getUsername();
        }
        return null;
    }

    public String userRoleAuthorised() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication != null && authentication.getAuthorities().stream().anyMatch(a -> a.getAuthority().equals("ADMIN"))) {
    return "ADMIN";
           }return "USER";
             }
    public String userPasswordAuthorised() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication != null) {
            UserDetails userDetails = (UserDetails) authentication.getPrincipal();
            return userDetails.getPassword();
        }
        return null;
    }


}