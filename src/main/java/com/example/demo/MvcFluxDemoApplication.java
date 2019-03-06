package com.example.demo;

import java.time.Duration;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.http.codec.ServerSentEvent;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import reactor.core.publisher.Flux;

@SpringBootApplication
@RestController
public class MvcFluxDemoApplication {
	
	@GetMapping(value = "/stream-stuff")
	public Flux<ServerSentEvent<String>> getMethodName() {
		return Flux.interval(Duration.ofSeconds(1)).take(5)
		.map(x -> {
			return ServerSentEvent.builder("Event "+x)
					.id(""+x)
					.event("Cool")
					.build();
		});
	}

	public static void main(String[] args) {
		SpringApplication.run(MvcFluxDemoApplication.class, args);
	}

}
