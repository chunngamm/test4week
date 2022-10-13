package com.sparta.homework;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import org.springframework.data.jpa.repository.config.EnableJpaAuditing;


@SpringBootApplication
@EnableJpaAuditing
public class CrudprojectApplication {

    public static void main(String[] args) {
        SpringApplication.run(CrudprojectApplication.class, args);
    }




}
