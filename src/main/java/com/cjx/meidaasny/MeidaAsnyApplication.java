package com.cjx.meidaasny;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;

@EnableAsync
@SpringBootApplication
public class MeidaAsnyApplication {

    public static void main(String[] args) {
        SpringApplication.run(MeidaAsnyApplication.class, args);
    }

}
