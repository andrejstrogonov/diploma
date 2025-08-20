package ru.skypro.homework.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
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
        /* if (userDetail==null) {
            UserDetails user = User.builder()
                     .password("password")
                     .username("user@gmail.com")
                     .roles("USER")
                     .build());
            userRepository.saveAdd(user.getUsername(),user.getPassword(),user.getAuthorities().toString());
        }

         */
        return userDetail;
    }
    public boolean userExists(String username){
        if(userRepository.findByUsername(username)==null){
            return false;
        }
        return true;
    }
    public void createUser(UserDetails userDetails){
        userRepository.saveAdd(userDetails.getUsername(),userDetails.getPassword(),userDetails.getAuthorities().toString());
    }
}
