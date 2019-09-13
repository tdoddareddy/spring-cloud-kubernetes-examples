package io.pivotal.pace;

import java.net.URI;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;

@RestController
public class ClientController {
	
    @Bean
    @LoadBalanced
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }

    @Autowired
    private RestTemplate restTemplate;

    @RequestMapping("/greeting")
    @HystrixCommand(fallbackMethod = "fallbackPhrase")
	public String phrase() {
		
		URI uri = UriComponentsBuilder.fromUriString("http://sb-service/greeting")
	            .build()
	            .toUri();
		
		String message = restTemplate.getForObject(uri, String.class);
		return "Greeting is " + message;
	}
	
	public String fallbackPhrase() {
		return "Greeting is gibberish!!";
	}


	@RequestMapping("/")
	@HystrixCommand(fallbackMethod = "fallback")
	public String language() {

		URI uri = UriComponentsBuilder.fromUriString("http://sb-service/language")
				.build()
				.toUri();

		String language = restTemplate.getForObject(uri, String.class);
		return "Greeting language is " + language;
	}

	public String fallback() {
		return "Greeting language is gibberish!!";
	}
}
