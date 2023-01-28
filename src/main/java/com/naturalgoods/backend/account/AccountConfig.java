package com.naturalgoods.backend.account;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
public class AccountConfig {

    @Bean
    CommandLineRunner accountRunner(AccountRepository repository) {
        return args -> {
            Account meiram = new Account("Meiram", "Shunshalin");
            repository.saveAll(List.of(meiram));
        };


    }
}
