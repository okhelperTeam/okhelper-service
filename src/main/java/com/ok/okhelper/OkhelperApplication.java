package com.ok.okhelper;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import tk.mybatis.spring.annotation.MapperScan;

/**
 *  @SpringbootApplication 相当于 @Configuration, @EnableAutoConfiguration 和 @ComponentScan 并具有他们的默认属性值
 */

@SpringBootApplication
@EnableTransactionManagement
@MapperScan(basePackages = {"com.ok.okhelper.dao"})
public class OkhelperApplication {

    public static void main(String[] args) {
        SpringApplication.run(OkhelperApplication.class, args);
    }
}
