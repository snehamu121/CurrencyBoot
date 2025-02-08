package Currency.CurrencyBoot.Dao;

import java.math.BigDecimal;

import javax.annotation.sql.DataSourceDefinition;




public class CurrencyResponse {

	private String from;
    private String to;
    private BigDecimal originalAmount;
    private BigDecimal convertedAmount;

    // ✅ Correct Constructor (Matches Class Name)
    public CurrencyResponse(String from, String to, BigDecimal originalAmount, BigDecimal convertedAmount) {
        this.from = from;
        this.to = to;
        this.originalAmount = originalAmount;
        this.convertedAmount = convertedAmount;
    }

    // Getters (Optional)
    public String getFrom() { return from; }
    public String getTo() { return to; }
    public BigDecimal getOriginalAmount() { return originalAmount; }
    public BigDecimal getConvertedAmount() { return convertedAmount; }
}
