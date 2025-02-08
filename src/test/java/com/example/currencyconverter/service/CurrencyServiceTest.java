package com.example.currencyconverter.service;

import static org.mockito.Mockito.*;
import static org.mockito.ArgumentMatchers.*;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.web.client.RestTemplate;

import Currency.CurrencyBoot.Dao.ExchangeRateResponse;
import Currency.CurrencyBoot.Service.CurrencyService;

import java.math.BigDecimal;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;


class CurrencyServiceTest {
	
	@Mock
    private RestTemplate restTemplate;

    @InjectMocks
    private CurrencyService currencyService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testConvertCurrency() {
        // Use HashMap instead of Map.of()
        Map<String, BigDecimal> mockRates = new HashMap<>();
        mockRates.put("USD", BigDecimal.ONE);
        mockRates.put("EUR", new BigDecimal("0.85"));

        // Mocking the getExchangeRates() method
        when(currencyService.getExchangeRates()).thenReturn(mockRates);

        // Perform conversion
        BigDecimal result = currencyService.convertCurrency("USD", "EUR", new BigDecimal("100"));

        // Validate result (100 * 0.85 / 1 = 85.0000)
        assertEquals(new BigDecimal("85.0000"), result);
    }
	

}
