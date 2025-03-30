//package edu.uth.tiemchungjava.config;
//
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.security.config.annotation.web.builders.HttpSecurity;
//import org.springframework.security.core.userdetails.User;
//import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
//import org.springframework.security.crypto.password.PasswordEncoder;
//import org.springframework.security.web.SecurityFilterChain;
//
//@Configuration
//public class SecurityConfig {
//
//    @Bean
//    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
//        http
//                .authorizeHttpRequests(authorizeRequests ->
//                        authorizeRequests
//                                .requestMatchers("/login", "/register", "/static/**").permitAll() // Cho phép truy cập các trang đăng nhập và đăng ký
//                                .requestMatchers("/indexx", "/gioithieu", "/doingu", "/lichtiem", "/quytrinh").authenticated() // Các trang yêu cầu người dùng đã đăng nhập
//                                .anyRequest().authenticated() // Các trang khác yêu cầu đăng nhập
//                )
//                .formLogin(form -> form
//                        .loginPage("/login") // Trang đăng nhập
//                        .permitAll()
//                )
//                .logout(logout -> logout
//                        .permitAll()
//                );
//        return http.build();
//    }
//
//    @Bean
//    public PasswordEncoder passwordEncoder() {
//        return new BCryptPasswordEncoder();
//    }
//}
