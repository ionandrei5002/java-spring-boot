package com.spring.gameloft.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Configuration
@EnableGlobalMethodSecurity(prePostEnabled = true, securedEnabled = true, jsr250Enabled = true)
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    private static final String ADMIN_ROLE = "ADMIN";
    private static final String USER_ROLE = "USER";

    @Autowired
    public void configureAuth(AuthenticationManagerBuilder auth) throws Exception {
        auth.inMemoryAuthentication()
                .withUser("admin")
                .password(passwordEncoder().encode("admin"))
                .roles(ADMIN_ROLE)
                .and()
                .withUser("user")
                .password(passwordEncoder().encode("user"))
                .roles(USER_ROLE);
    }
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .csrf()
                .disable()
                .authorizeRequests()
//                .antMatchers(HttpMethod.GET, "/singers").hasRole(USER_ROLE)
//                .antMatchers(HttpMethod.GET, "/singers/**").hasRole(USER_ROLE)
//                .antMatchers(HttpMethod.POST, "/singers/**").hasRole(ADMIN_ROLE)
//                .antMatchers(HttpMethod.PUT, "/singers/**").hasRole(ADMIN_ROLE)
//                .antMatchers(HttpMethod.DELETE, "/singers/**").hasRole(ADMIN_ROLE)
                .anyRequest()
                .authenticated()
                .and()
                .httpBasic();
    }

    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
