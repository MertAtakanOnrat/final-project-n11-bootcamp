package com.mertoatakan.userservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.scheduling.annotation.EnableAsync;

@SpringBootApplication
@EnableAsync
@EnableFeignClients
public class UserserviceApplication {

	public static void main(String[] args) { SpringApplication.run(UserserviceApplication.class, args); }

}
