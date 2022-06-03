package com.example.demo.user;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Configuration

public class UserConfig{

    @Bean
    CommandLineRunner commandLineRunner(UserRepository repository){
        return args -> {
            User mariam =new User(
                    "Mariam",
                    "mariam@gmail.com",
                    "mariam123",
                    "admin"

            );

            User ahmed =new User(
                    "Ahmed",
                    "ahmed@gmail.com",
                    "ahmed123",
                    "standard"

            );

            repository.saveAll(
                    List.of(mariam,ahmed)
            );
        };
    }
}
