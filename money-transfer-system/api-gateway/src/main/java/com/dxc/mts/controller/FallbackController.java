package com.dxc.mts.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import reactor.core.publisher.Mono;

@RestController
public class FallbackController {

	@RequestMapping("/statementFallback")
	public Mono<String> statementServiceFallback() {
		return Mono.just("Statement Service is taking too long to respond or it is down. Please try again");
	}

	@RequestMapping("/transferFallback")
	public Mono<String> transferServiceFallback() {
		return Mono.just("Transfer Service is taking too long to respond or it is down. Please try again");
	}
}
