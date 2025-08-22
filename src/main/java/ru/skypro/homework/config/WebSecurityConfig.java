package ru.skypro.homework.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import ru.skypro.homework.dto.Role;
import ru.skypro.homework.repository.UserRepository;
import ru.skypro.homework.service.impl.MyUserDetailService;


@EnableWebSecurity
@Configuration
public class WebSecurityConfig {
    private final MyUserDetailService myUserDetailService;
   @Autowired
    UserRepository userRepository;

    public WebSecurityConfig(MyUserDetailService myUserDetailService) {
        this.myUserDetailService = myUserDetailService;
    }

    private static final String[] AUTH_WHITELIST = {
            "/swagger-resources/**",
            "/swagger-ui.html",
            "/v3/api-docs",
            "/webjars/**",
            "/login",
            "/register",
            "/users/me"
              };

    @Bean
    public DaoAuthenticationProvider authenticationProvider() {
                if(userRepository.findString()==0){
            UserDetails user =
                    User.builder()
                            .username("user@gmail.com")
                            .password("password")
                            .passwordEncoder(passwordEncoder()::encode)
                            .roles(Role.USER.name())
                            .build();
userRepository.saveAdd("user@gmail.com",user.getPassword(),"USER");
        }
        DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
        provider.setUserDetailsService(myUserDetailService);
        provider.setPasswordEncoder(passwordEncoder());
        return provider;
    }

     @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http.csrf(AbstractHttpConfigurer::disable)
                .cors(c->{
                    CorsConfigurationSource source= request->{
                        CorsConfiguration config=new CorsConfiguration();
                        config.addAllowedOriginPattern("http://localhost:3000"); // фронт
                        config.addAllowedHeader("*");
                        config.addAllowedMethod("*");
                        config.setAllowCredentials(true);
                        return config;
                    };
                    c.configurationSource(source);
                })
                .httpBasic(Customizer.withDefaults())
                .authenticationProvider(authenticationProvider())
                .authorizeHttpRequests(
                        authorization ->
                                authorization
                                        .requestMatchers(HttpMethod.OPTIONS, "/**").permitAll()
                                        .requestMatchers(AUTH_WHITELIST).permitAll()
                                        .requestMatchers("/ads/**", "/users/**").authenticated()
                                        .anyRequest()
                                        .authenticated());
                                         return http.build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
     return new BCryptPasswordEncoder();
    }
}
