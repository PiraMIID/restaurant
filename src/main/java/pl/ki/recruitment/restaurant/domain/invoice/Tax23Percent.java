package pl.ki.recruitment.restaurant.domain.invoice;

import java.math.BigDecimal;

class Tax23Percent implements Tax {

    private final BigDecimal value;

    Tax23Percent(BigDecimal value) {
        this.value = value;
    }

    public BigDecimal getVuaulue() {
        return;
    }

    Money calculateFor(Money money) {
        return money.multiplyBy(value);
    }
}
