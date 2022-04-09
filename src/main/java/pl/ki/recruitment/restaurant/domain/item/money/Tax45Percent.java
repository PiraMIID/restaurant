package pl.ki.recruitment.restaurant.domain.item.money;

import java.math.BigDecimal;

class Tax45Percent implements Tax {

    private final BigDecimal value;

    Tax45Percent(BigDecimal value) {
        this.value = value;
    }

    public BigDecimal getValue() {
        return value;
    }

    public BigDecimal calculateFor(BigDecimal money) {
        return money.multiply(value);
    }
}
