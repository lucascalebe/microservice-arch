package com.example.microservices.currencyexchangeservice;

import java.math.BigDecimal;

public class CurrencyExchange {
  private Long id;
  private String from;
  private String to;
  private BigDecimal conversionMutiple;

  public CurrencyExchange() {
  }

  public CurrencyExchange(Long id, String from, String to, BigDecimal conversionMutiple) {
    this.id = id;
    this.from = from;
    this.to = to;
    this.conversionMutiple = conversionMutiple;
  }

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public String getFrom() {
    return from;
  }

  public void setFrom(String from) {
    this.from = from;
  }

  public String getTo() {
    return to;
  }

  public void setTo(String to) {
    this.to = to;
  }

  public BigDecimal getConversionMutiple() {
    return conversionMutiple;
  }

  public void setConversionMutiple(BigDecimal conversionMutiple) {
    this.conversionMutiple = conversionMutiple;
  }
}
