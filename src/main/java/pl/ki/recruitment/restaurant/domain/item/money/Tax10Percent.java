package pl.ki.recruitment.restaurant.domain.item.money;

import java.math.BigDecimal;

class Tax10Percent implements Tax {

    private final BigDecimal value;

    Tax10Percent() {
        this.value = new BigDecimal(0.1);
    }

    public BigDecimal getValue() {
        return value;
    }

    public BigDecimal calculateFor(BigDecimal money) {
        return money.multiply(value);
    }
}
