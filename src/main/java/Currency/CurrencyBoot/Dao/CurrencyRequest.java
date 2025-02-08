package Currency.CurrencyBoot.Dao;

import java.math.BigDecimal;

import lombok.NoArgsConstructor;

@NoArgsConstructor
public class CurrencyRequest {

	private String from;
    private String to;
    private BigDecimal amount;

    public String getFrom() 
    { return from; }
    public void setFrom(String from) { this.from = from; }

    public String getTo() { return to; }
    public void setTo(String to) { this.to = to; }

    public BigDecimal getAmount() { return amount; }
    public void setAmount(BigDecimal amount) { this.amount = amount; }

    @Override
    public String toString() {
        return "CurrencyRequest{from='" + from + "', to='" + to + "', amount=" + amount + "}";
    }
}
