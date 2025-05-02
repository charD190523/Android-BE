package com.example.bhd.security;

import com.example.bhd.enums.Role;
import com.example.bhd.security.jwt.JwtRequestFilter;
import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@AllArgsConstructor
public class SecurityConfig {

    private final JwtRequestFilter jwtRequestFilter;

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }

    @Bean
    SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        return http
                .csrf(AbstractHttpConfigurer::disable)
                .authorizeHttpRequests(auth -> auth
                                .requestMatchers("/api/auth/**").permitAll()
                                .requestMatchers("/api/users/change-password"
                                                ,"/api/user/update-infor"
                                                ,"/api/seat-detail/**"
                                                ,"/api/showtimes/getByMovie"
                                                ,"/api/users/confirm-change-password"
                                                ,"/api/food/all"
                                                ,"/api/invoice/**"
                                                ,"/api/payment/**"
                                                , "/api/logout/**").hasAnyRole(Role.USER.name(), Role.ADMIN.name())
                                .requestMatchers("/api/movies/admin/**"
                                                ,"/api/showtimes/admin/**"
                                                ,"/api/food/admin/**").hasRole(Role.ADMIN.name())
                                .anyRequest().authenticated()
                )
//                .oauth2Login(oauth2 -> oauth2
//                        .userInfoEndpoint(userInfo -> userInfo
//                                .userService(customOAuth2UserServiceIml)
//                        )
//                        .defaultSuccessUrl("/api/login/oauth2/success", true)
//                )
//                .logout(logout -> logout
//                        .logoutUrl("/api/logout")
//                        .logoutSuccessUrl("/api/home") // Chuyển hướng sau khi logout
//                        .invalidateHttpSession(true) // Xóa session
//                        .deleteCookies("JSESSIONID") // Xóa cookie phiên đăng nhập
//                        .clearAuthentication(true)
//                )
//                .exceptionHandling(exception -> exception
//                        .authenticationEntryPoint(authenticationEntryPoint) // Xử lý lỗi 401
//                        .accessDeniedHandler(accessDeniedHandler) // Xử lý lỗi 403
//                )
                .addFilterBefore(jwtRequestFilter, UsernamePasswordAuthenticationFilter.class)
                .build();
    }

}
