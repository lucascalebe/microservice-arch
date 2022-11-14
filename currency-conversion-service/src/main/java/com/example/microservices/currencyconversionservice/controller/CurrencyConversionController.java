package com.example.microservices.currencyconversionservice.controller;

import com.example.microservices.currencyconversionservice.CurrencyConversion;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;

@RestController
@RequestMapping("currency-conversion")
public class CurrencyConversionController {

  @GetMapping("/from/{from}/to/{to}/quantity/{quantity}")
  public CurrencyConversion calculateCurrencyConversion(@PathVariable String from, @PathVariable String to,
                                                        @PathVariable BigDecimal quantity) {
    return new CurrencyConversion(10001L, from, to, quantity, BigDecimal.TEN, BigDecimal.ONE, "");
  }
}
