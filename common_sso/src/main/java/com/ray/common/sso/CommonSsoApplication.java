package com.ray.common.sso;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = "com.ray")
public class CommonSsoApplication {

	public static void main(String[] args) {
		SpringApplication.run(CommonSsoApplication.class, args);
	}

}

