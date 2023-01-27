package com.naturalgoods.backend.product;


import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
public class ProductConfig {

    @Bean
    CommandLineRunner productRunner(ProductRepository productRepository) {
        return args -> {
            productRepository.saveAll(List.of(new Product("kumys", "empty")));
        };
    }
}
