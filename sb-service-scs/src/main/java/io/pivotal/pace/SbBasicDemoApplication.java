package io.pivotal.pace;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class SbBasicDemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(SbBasicDemoApplication.class, args);
	}
}
