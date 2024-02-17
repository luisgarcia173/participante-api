package com.luisgarcia;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.test.context.TestConfiguration;

@TestConfiguration(proxyBeanMethods = false)
public class TestParticipanteApiApplication {

	public static void main(String[] args) {
		SpringApplication.from(ParticipanteApiApplication::main).with(TestParticipanteApiApplication.class).run(args);
	}

}
