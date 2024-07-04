package com.evoke.springbootproject;

import org.springframework.boot.SpringApplication;

import org.springframework.boot.autoconfigure.SpringBootApplication;




@SpringBootApplication(scanBasePackages={
"com.evoke.springbootproject"})
public class SpringbootprojectApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringbootprojectApplication.class, args);
	}

}
