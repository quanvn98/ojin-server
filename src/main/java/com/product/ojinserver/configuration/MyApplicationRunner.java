package com.product.ojinserver.configuration;

import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import com.product.ojinserver.service.SeedingService;

@Component
public class MyApplicationRunner implements ApplicationRunner {
	
	private final SeedingService seedingService;

	public MyApplicationRunner(SeedingService _seedingService) {
		this.seedingService = _seedingService;
	}

	@Override
	public void run(ApplicationArguments args) throws Exception {
		seedingService.seedArticles();

		seedingService.seedRoles();

		seedingService.seedUsers();
	}

}
