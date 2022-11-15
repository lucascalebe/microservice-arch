package com.example.microservices.currencyexchangeservice.controller;

import io.github.resilience4j.retry.annotation.Retry;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
public class CircuitBreakerController {

  private final Logger logger = LoggerFactory.getLogger(CircuitBreakerController.class);

  @GetMapping("/sample-api")
  @Retry(name = "sample-api-retry", fallbackMethod = "hardcodedResponse")
  public String sampleApi() {
    logger.info("Sample api call received");
    var response = new RestTemplate().getForEntity("http://localhost:8080/some-dummy-url", String.class);

    return response.getBody();
  }

  public String hardcodedResponse(Exception ex) {
    return "fallback-response";
  }
}
