package com.example.demo.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.example.demo.services.JpaUserDetailsService;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private JpaUserDetailsService userDetailsService;

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService)
            .passwordEncoder(NoOpPasswordEncoder.getInstance()); // Use NoOpPasswordEncoder for plain text passwords
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable()
            .authorizeRequests()
            .antMatchers("/swagger-ui.html/**").permitAll()
            .antMatchers("/api/csv/addUser/").permitAll() // Allow access to /addUser without authentication
            .antMatchers("/api/csv/**").authenticated() // Protect the '/api/csv' endpoints
            .and()
            .httpBasic();
        http.cors().and()
            .csrf().disable()
            .authorizeRequests()
            .antMatchers("/**").fullyAuthenticated()
            .and()
            .httpBasic();
    }
}
