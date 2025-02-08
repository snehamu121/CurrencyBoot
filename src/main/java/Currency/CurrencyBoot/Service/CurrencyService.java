package Currency.CurrencyBoot.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import Currency.CurrencyBoot.Dao.ExchangeRateResponse;

@Service
public class CurrencyService {

	private static final Map<String, BigDecimal> exchangeRates = new HashMap<>();

    // Initializing exchange rates
    static {
        exchangeRates.put("USD", BigDecimal.ONE);
        exchangeRates.put("EUR", new BigDecimal("0.85"));
        exchangeRates.put("INR", new BigDecimal("74.5"));
        exchangeRates.put("GBP", new BigDecimal("0.75"));
        exchangeRates.put("JPY", new BigDecimal("110.0"));
    }

    // Fetch exchange rates
    public Map<String, BigDecimal> getExchangeRates() {
        return exchangeRates;
    }

    // Convert currency
    public BigDecimal convertCurrency(String from, String to, BigDecimal amount) {
        if (!exchangeRates.containsKey(from) || !exchangeRates.containsKey(to)) {
            throw new IllegalArgumentException("Invalid currency code: " + from + " or " + to);
        }
        
        BigDecimal fromRate = exchangeRates.get(from);
        BigDecimal toRate = exchangeRates.get(to);

        // Convert amount
        return amount.multiply(toRate).divide(fromRate, 2, BigDecimal.ROUND_HALF_UP);
    }
}
