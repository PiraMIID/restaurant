package pl.ki.recruitment.restaurant.domain.invoice;

import java.math.BigDecimal;

class Tax8Percent implements Tax {

    private final BigDecimal value;

    Tax8Percent(BigDecimal value) {
        this.value = value;
    }

    public BigDecimal getVuaulue() {
        return value;
    }

    Money calculateFor(Money money) {
        return money.multiplyBy(value);
    }
}
