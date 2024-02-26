package com.backend.show;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.PropertySource;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
@PropertySource({
		"file:${props.dir}/persistence-mongo.properties",
		"file:${props.dir}/application.properties"
})
public class ShowApplication {

	public static void main(String[] args) {
		SpringApplication.run(ShowApplication.class, args);
		System.out.println("http://localhost:8080/start/health-check");
	}

}
