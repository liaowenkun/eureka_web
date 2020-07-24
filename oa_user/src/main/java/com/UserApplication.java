package com;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@SpringBootApplication
@EnableEurekaClient               //开启eureka客户端
@EnableTransactionManagement      //开启事务
@MapperScan(value = "com.mapper") //扫描接口包
public class UserApplication {


    public static void main(String[] args) {

        SpringApplication.run(UserApplication.class, args);
    }


}
