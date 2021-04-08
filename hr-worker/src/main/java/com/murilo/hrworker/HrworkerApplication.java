package com.murilo.hrworker;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@EnableEurekaClient
@SpringBootApplication
public class HrworkerApplication {

    public static void main(String[] args) {
        SpringApplication.run(HrworkerApplication.class, args);
    }

}
