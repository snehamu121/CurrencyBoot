package Currency.CurrencyBoot.Exception;

import java.util.HashMap;
import java.util.Map;


import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.http.HttpStatus;

@RestControllerAdvice
public class GlobalExceptionHandler {
	
	@ExceptionHandler(IllegalArgumentException.class)
	public ResponseEntity<Map<String, String>> handleInvalidCurrencyException(IllegalArgumentException ex) {
	    Map<String, String> errorResponse = new HashMap<>();
	    errorResponse.put("error", ex.getMessage());
	    return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorResponse);
	}
}
