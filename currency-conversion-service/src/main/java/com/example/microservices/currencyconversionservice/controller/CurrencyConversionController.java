package com.example.microservices.currencyconversionservice.controller;

import com.example.microservices.currencyconversionservice.CurrencyConversion;
import com.example.microservices.currencyconversionservice.proxy.CurrencyExchangeProxy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.math.BigDecimal;
import java.util.HashMap;

@RestController
@RequestMapping("currency-conversion")
public class CurrencyConversionController {

  @Autowired
  private CurrencyExchangeProxy proxy;

  @GetMapping("/from/{from}/to/{to}/quantity/{quantity}")
  public CurrencyConversion calculateCurrencyConversion(@PathVariable String from, @PathVariable String to,
                                                        @PathVariable BigDecimal quantity) {

    HashMap<String, String> uriVariables = new HashMap<>();
    uriVariables.put("from", from);
    uriVariables.put("to", to);

    ResponseEntity<CurrencyConversion> responseEntity = new RestTemplate().getForEntity("http://localhost:8000/currency-exchange/from/{from}/to/{to}",
            CurrencyConversion.class, uriVariables);

    CurrencyConversion currencyConversion = responseEntity.getBody();
    BigDecimal conversionMultiple = currencyConversion.getConversionMultiple();

    return new CurrencyConversion(10001L, from, to, quantity, conversionMultiple, quantity.multiply(conversionMultiple),
            currencyConversion.getEnvironment() + " " + "rest template");
  }

  @GetMapping("/feign/from/{from}/to/{to}/quantity/{quantity}")
  public CurrencyConversion calculateCurrencyConversionFeign(@PathVariable String from, @PathVariable String to,
                                                        @PathVariable BigDecimal quantity) {

    CurrencyConversion currencyConversion = proxy.retrieveExchangeValue(from, to);
    BigDecimal conversionMultiple = currencyConversion.getConversionMultiple();

    return new CurrencyConversion(10001L, from, to, quantity, conversionMultiple, quantity.multiply(conversionMultiple),
            currencyConversion.getEnvironment() + " " + "feign");
  }
}
