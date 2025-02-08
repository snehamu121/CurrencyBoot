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



@Service
public class CurrencyService {

	private static final String API_URL = "https://api.exchangerate-api.com/v4/latest/";

    private final RestTemplate restTemplate;

    public CurrencyService() {
        this.restTemplate = new RestTemplate();
    }

    // Method to fetch exchange rates for a given base currency
    public Map<String, Object> getExchangeRates(String base) {
        String url = API_URL + base;
        return restTemplate.getForObject(url, Map.class);
    }

    // Method to convert currency from one type to another
    public BigDecimal convertCurrency(String from, String to, BigDecimal amount) {
        Map<String, Object> response = getExchangeRates(from);
        
        if (response == null || !response.containsKey("rates")) {
            throw new RuntimeException("Failed to fetch exchange rates");
        }

        Map<String, Object> rates = (Map<String, Object>) response.get("rates");

        if (!rates.containsKey(to)) {
            throw new IllegalArgumentException("Invalid currency code: " + to);
        }

        BigDecimal exchangeRate = new BigDecimal(rates.get(to).toString());
        return amount.multiply(exchangeRate);
    }
}
