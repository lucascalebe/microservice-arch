package com.example.microservices.currencyexchangeservice.repository;

import com.example.microservices.currencyexchangeservice.CurrencyExchange;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CurrencyExchangeRepository extends JpaRepository<CurrencyExchange, Long> {
  Optional<CurrencyExchange> findByFromAndTo(String from, String to);
}
