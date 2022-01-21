package com.ariel.restfulwebservices.security;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Configuration
public class SecutiryConfiguration extends WebSecurityConfigurerAdapter {

    // Create user
    @Override
    public void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.inMemoryAuthentication().passwordEncoder(getPasswordEncoder())
                .withUser("ariel").password("$2y$10$Miijgm4UpplI5gIckJz/Z.Si24n7m.zHSZV9vmHDmq6XdleYPZtlW")
                .roles("USER", "ADMIN");
    }

    private BCryptPasswordEncoder getPasswordEncoder() {
        return new BCryptPasswordEncoder(10);
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.httpBasic().and().authorizeRequests()
                .antMatchers("/login").permitAll()
                .antMatchers("/users", "/users/**", "/filter-users/**", "/hello-world").hasRole("USER")
                .antMatchers("/**").hasRole("ADMIN")
        ;

        http.csrf().disable();
        http.headers().frameOptions().disable();
    }
}
