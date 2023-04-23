package com.jpan.jokey;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class JokeyApplication {

	public static void main(String[] args) {
		SpringApplication.run(JokeyApplication.class, args);
	}

}
