package com.ok.okhelper;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import tk.mybatis.spring.annotation.MapperScan;

@SpringBootApplication
@ComponentScan
@MapperScan(basePackages = {"com.ok.okhelper.dao"})
public class OkhelperApplication {

    public static void main(String[] args) {
        SpringApplication.run(OkhelperApplication.class, args);
    }
}
