package com.jt;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.jt.mapper") //Give the mapper interface under this path to the container to manage
public class JtApplication {

    public static void main(String[] args) {
        SpringApplication.run(JtApplication.class, args);
    }

}
