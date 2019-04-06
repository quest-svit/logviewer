package com.dange.tanmay.server.main;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.web.servlet.error.ErrorMvcAutoConfiguration;


@EnableAutoConfiguration(exclude = {ErrorMvcAutoConfiguration.class})
@SpringBootApplication(scanBasePackages = {"com.dange.tanmay"})
public class LogViewerApplication {

	public static void main(String[] args) {
		SpringApplication.run(LogViewerApplication.class, args);
	}

}
