package com.appvendas;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class AppvendasApplication {

	public static void main(String[] args) {
		SpringApplication.run(AppvendasApplication.class, args);
		
		//System.out.println(new BCryptPasswordEncoder().encode("ti@mendes"));
	}

}
