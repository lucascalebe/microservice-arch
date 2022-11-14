package com.example.microservices.currencyexchangeservice.controller;

import com.example.microservices.currencyexchangeservice.CurrencyExchange;
import com.example.microservices.currencyexchangeservice.repository.CurrencyExchangeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;

@RestController
@RequestMapping("currency-exchange")
public class CurrencyExchangeController {

  @Autowired
  private CurrencyExchangeRepository currencyExchangeRepository;

  @Autowired
  private Environment environment;

  @GetMapping("/from/{from}/to/{to}")
  public CurrencyExchange retrieveExchangeValue(@PathVariable String from, @PathVariable String to) {
    CurrencyExchange currencyExchange = currencyExchangeRepository.findByFromAndTo(from, to)
            .orElseThrow(() -> new RuntimeException("Unable to find data for " + from + " to " + to));

    String port = environment.getProperty("local.server.port");
    currencyExchange.setEnvironment(port);
    return currencyExchange;
  }
}
