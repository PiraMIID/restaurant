package pl.ki.recruitment.restaurant.domain.item.money;

import java.math.BigDecimal;

public class Tax5Percent implements Tax {

    private final BigDecimal value;

    public Tax5Percent() {
        this.value = new BigDecimal(0.05);
    }

    public BigDecimal getValue() {
        return value;
    }

    @Override
    public BigDecimal calculateFor(BigDecimal money) {
        return money.multiply(value);
    }
}
