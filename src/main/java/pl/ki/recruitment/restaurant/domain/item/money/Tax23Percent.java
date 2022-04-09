package pl.ki.recruitment.restaurant.domain.item.money;

import java.math.BigDecimal;

class Tax23Percent implements Tax {

    private final BigDecimal value;

    Tax23Percent(BigDecimal value) {
        this.value = value;
    }

    public BigDecimal getValue() {
        return null;
    }

    public BigDecimal calculateFor(BigDecimal money) {
        return money.multiply(value);
    }
}
