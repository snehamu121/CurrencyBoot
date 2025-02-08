package Currency.CurrencyBoot.Controller;

import java.math.BigDecimal;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.databind.deser.Deserializers.Base;

import Currency.CurrencyBoot.Dao.CurrencyRequest;
import Currency.CurrencyBoot.Dao.CurrencyResponse;
import Currency.CurrencyBoot.Service.CurrencyService;

@RestController
@RequestMapping("/api")
public class CurrencyController {
	
	@Autowired  // Add this annotation to inject the service
    private CurrencyService currencyService;

    @GetMapping("/rates")
    public Map<String, Object> getExchangeRates(@RequestParam(defaultValue = "USD") String base) {
        return currencyService.getExchangeRates(base);
    }

    @PostMapping("/convert")
    public ResponseEntity<?> convert(@RequestBody CurrencyRequest request) {
        try {
            // Validate that required fields are not null
            if (request.getFrom() == null || request.getTo() == null || request.getAmount() == null) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                        .body(Collections.singletonMap("error", "Missing required fields"));
            }

            // Perform currency conversion
            BigDecimal convertedAmount = currencyService.convertCurrency(request.getFrom(), request.getTo(), request.getAmount());

            // Return response
            return ResponseEntity.ok(new CurrencyResponse(request.getFrom(), request.getTo(), request.getAmount(), convertedAmount));
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(Collections.singletonMap("error", e.getMessage()));
        }
    }
}
