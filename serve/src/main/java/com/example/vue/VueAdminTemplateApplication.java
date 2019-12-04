package com.example.vue;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.example.vue.mapper")
public class VueAdminTemplateApplication {

    public static void main(String[] args) {
        SpringApplication.run(VueAdminTemplateApplication.class, args);
    }

}
