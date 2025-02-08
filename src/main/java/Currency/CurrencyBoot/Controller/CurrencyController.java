package Currency.CurrencyBoot.Controller;

import java.math.BigDecimal;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import Currency.CurrencyBoot.Dao.CurrencyRequest;
import Currency.CurrencyBoot.Dao.CurrencyResponse;
import Currency.CurrencyBoot.Service.CurrencyService;

@RestController
@RequestMapping("/api")
public class CurrencyController {
	
	@Autowired
	private final CurrencyService currencyService;

    public CurrencyController(CurrencyService currencyService) {
        this.currencyService = currencyService;
    }
	
	@GetMapping("/rates")
    public ResponseEntity<Map<String, BigDecimal>> getExchangeRates() {
        Map<String, BigDecimal> rates = currencyService.getExchangeRates();
        return ResponseEntity.ok(rates);
    }

	@PostMapping("/convert")
	public ResponseEntity<?> convert(@RequestBody CurrencyRequest request) {
	    System.out.println("Received request: " + request);

	    if (request.getFrom() == null || request.getTo() == null || request.getAmount() == null) {
	        Map<String, String> errorResponse = new HashMap<>();
	        errorResponse.put("error", "Missing required fields");
	        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorResponse);
	    }

	    BigDecimal convertedAmount = currencyService.convertCurrency(request.getFrom(), request.getTo(), request.getAmount());
	    return ResponseEntity.ok(new CurrencyResponse(request.getFrom(), request.getTo(), request.getAmount(), convertedAmount));
	}
}
