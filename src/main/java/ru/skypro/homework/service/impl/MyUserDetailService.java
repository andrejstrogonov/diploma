package ru.skypro.homework.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import ru.skypro.homework.repository.UserRepository;

@Service
public class MyUserDetailService implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
      //  PasswordEncoder passwordEncoder;
        UserDetails userDetail=userRepository.findByUsername(username);
             return userDetail;
    }
    public boolean userExists(String username){
        if(userRepository.findByUsername(username)==null){
                    return false;
        }
        return true;
    }
    public void createUser(UserDetails userDetails){
        userRepository.saveAdd(userDetails.getUsername(),userDetails.getPassword(),userRoleAuthorised());
    }
    private String userRoleAuthorised() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication != null && authentication.getAuthorities().stream().anyMatch(a -> a.getAuthority().equals("ADMIN"))) {
            return "ADMIN";
        }return "USER";
    }
}
