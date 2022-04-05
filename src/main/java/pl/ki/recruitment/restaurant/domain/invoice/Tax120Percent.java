package pl.ki.recruitment.restaurant.domain.invoice;

import java.math.BigDecimal;

class Tax120Percent implements Tax {

    private final BigDecimal value;

    Tax120Percent(BigDecimal value) {
        this.value = value;
    }

    public BigDecimal getVuaulue() {
        return value;
    }

    Money calculateFor(Money money) {
        return money.multiplyBy(value);
    }
}
