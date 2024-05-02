package com.lezhin;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;


@SpringBootApplication
@EnableCaching
@EnableFeignClients
@EnableJpaAuditing
public class LezhinApiApplication {
    public static void main(String[] args) {
        SpringApplication.run(LezhinApiApplication.class, args);
    }
}
