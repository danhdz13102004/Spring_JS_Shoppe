package com.example.my_shoppe.security;

import com.example.my_shoppe.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.provisioning.JdbcUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

import javax.sql.DataSource;
import java.sql.PreparedStatement;

@Configuration
public class MySecurity {
    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public DaoAuthenticationProvider authenticationProvider(UserService userService){
        DaoAuthenticationProvider daoAuthenticationProvider = new DaoAuthenticationProvider();
        daoAuthenticationProvider.setUserDetailsService(userService);
        daoAuthenticationProvider.setPasswordEncoder(passwordEncoder());
        return daoAuthenticationProvider;
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http.authorizeHttpRequests(
            configurer->configurer
//                    .requestMatchers("/").permitAll()
//                    .requestMatchers("/login/process").authenticated()
                        .anyRequest().permitAll()
        ).formLogin(
                form-> form.loginPage("/login/process")
                        .loginProcessingUrl("/authenticateTheUser")
                        .permitAll()

        ).logout(
                logout->logout
                        .logoutSuccessUrl("/")
                        .permitAll()
        );
        return http.build();
    }
}
