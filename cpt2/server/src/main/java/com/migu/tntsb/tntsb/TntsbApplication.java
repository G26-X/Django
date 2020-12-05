package com.migu.tntsb.tntsb;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan()
public class TntsbApplication {
    public static void main(String[] args) {
        SpringApplication.run(TntsbApplication.class, args);
    }
}
