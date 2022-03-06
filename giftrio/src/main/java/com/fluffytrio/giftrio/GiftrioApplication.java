package com.fluffytrio.giftrio;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class GiftrioApplication {

	public static void main(String[] args) {
		SpringApplication.run(GiftrioApplication.class, args);
	}

}
