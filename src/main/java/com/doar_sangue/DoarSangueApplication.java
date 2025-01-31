package com.doar_sangue;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication(scanBasePackages = {
		"com.doar_sangue",
})
@EnableAutoConfiguration
@ComponentScan
public class DoarSangueApplication {

	public static void main(String[] args) {
		SpringApplication.run(DoarSangueApplication.class, args);
	}

}
