package com.example.microservices.currencyexchangeservice.controller;

import io.github.resilience4j.bulkhead.annotation.Bulkhead;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.ratelimiter.annotation.RateLimiter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
public class CircuitBreakerController {

  private final Logger logger = LoggerFactory.getLogger(CircuitBreakerController.class);

  @GetMapping("/sample-api")
  @CircuitBreaker(name = "default", fallbackMethod = "hardcodedResponse")
  @Bulkhead(name = "default")
  // concorrent calls allowed
  public String sampleApi() {
    logger.info("Sample api call received");
    var response = new RestTemplate().getForEntity("http://localhost:8080/some-dummy-url", String.class);

    return response.getBody();
  }

  public String hardcodedResponse(Exception ex) {
    return "fallback-response";
  }
}
