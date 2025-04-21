package edu.uth.tiemchungjava.config;

import edu.uth.tiemchungjava.models.AuthenticationSuccessHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractAuthenticationFilterConfigurer;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SercurityConfiguration {

    @Autowired
    private UserDetailsService userDetailsService;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {
        return httpSecurity
                .csrf(AbstractHttpConfigurer::disable)
                .authorizeHttpRequests(registry->{
                    registry.requestMatchers("/index", "/doingu", "/gioithieu", "/lichtiem", "/lydatlichtiem", "/quytrinh", "/Vaccine"
                            ,"/register/**", "/img/**", "/assets/**", "/bootstrap/**"
                            , "/css/**", "/js/**", "/pages/**", "/webfonts/**" , "/admin/**", "/categoryAdmin" , "/"
                            , "/vaccines", "/chatbox", "datlichtiem").permitAll();
                    registry.requestMatchers("/admin/** " , "admin" , "donhang" , "lichsu").hasRole("ADMIN");
                    registry.requestMatchers("/datlichtiem" ).hasRole("USER");
                    registry.anyRequest().authenticated();
                })
                .formLogin(httpSecurityFormLoginConfigurer -> {
                    httpSecurityFormLoginConfigurer
                            .loginPage("/login")
                            .successHandler(new AuthenticationSuccessHandler())
                            .permitAll();
                })
                .logout(logout -> logout
                        .logoutUrl("/logout")  // Định nghĩa URL đăng xuất
                        .invalidateHttpSession(true)  // Xóa session khi đăng xuất
                        .deleteCookies("JSESSIONID") // Xóa cookie
                        .logoutSuccessUrl("/login") // Redirect về trang đăng nhập sau khi logout
                        .permitAll()) // Cho phép logout mọi lúc
                .sessionManagement(session ->
                        session.invalidSessionUrl("/login")  // Đảm bảo rằng khi session hết hạn, sẽ chuyển hướng về trang đăng nhập
                                .maximumSessions(1)  // Giới hạn chỉ một phiên đăng nhập tại một thời điểm
                                .expiredUrl("/login") // Chuyển hướng khi session hết hạn
                )
                .build();
    }

//    @Bean
//    public UserDetailsService userDetailsService() {
//        UserDetails normalUser = User.builder()
//                .username("gc")
//                .password("$2a$12$9hSP9pPC4kYrwuU9F3bL8O.EW40i9GgdUFwiWsYlbdMuQcn3fbUNC") //1234
//                .roles("USER")
//                .build();
//        UserDetails adminUser = User.builder()
//                .username("admin")
//                .password("$2a$12$Ud52w/IBXQSkQ0KLNUZ7sefxkwsx6h4.hPDhp87/fPiW0fP4FdjVu") //9876
//                .roles("ADMIN", "USER")
//                .build();
//        return new InMemoryUserDetailsManager(normalUser, adminUser);
//    }

    @Bean
    public UserDetailsService userDetailsService() {
        return userDetailsService;
    }

    @Bean
    public AuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
        provider.setUserDetailsService(userDetailsService);
        provider.setPasswordEncoder(passwordEncoder());
        return provider;
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
