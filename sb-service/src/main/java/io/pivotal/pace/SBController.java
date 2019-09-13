package io.pivotal.pace;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class SBController {
	
	private GreetingRepository greetingRepository;
	
	@Autowired
	private GreetingConfig config;
	
	public SBController(GreetingRepository greetingRepository) {
		this.greetingRepository = greetingRepository;
	}
	
	@RequestMapping("/")
	public String greetingLanguage() {
		return "Greeting language is " + config.getLanguage();
	}
	
	@RequestMapping("/greeting")
	public String greeting() {
		List<Greeting> greeting = greetingRepository.findByLanguage(config.getLanguage());
		if (greeting.isEmpty()) 
			return "Greeting not found for " + config.getLanguage();
		else
			return greeting.get(0).getText();
	}

	@RequestMapping("/language")
	public String language() {
		return config.getLanguage();
	}
}
