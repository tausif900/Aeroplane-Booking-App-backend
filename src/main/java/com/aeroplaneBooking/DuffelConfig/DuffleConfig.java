package com.aeroplaneBooking.DuffelConfig;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestClient;

@Configuration
public class DuffleConfig {

	@Value("${duffel.api.token}")
	private String duffelToken;

	@Bean
	public RestClient restClient() {
		RestClient restClient = RestClient.builder().baseUrl("https://api.duffel.com")
				.defaultHeader("Authorization", "Bearer " + duffelToken).defaultHeader("Duffel-Version", "v2").build();
		return restClient;
	}

}
