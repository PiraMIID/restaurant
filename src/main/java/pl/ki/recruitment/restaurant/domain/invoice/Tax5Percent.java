package pl.ki.recruitment.restaurant.domain.invoice;

import java.math.BigDecimal;

class Tax5Percent implements Tax {

    private final BigDecimal value;

    Tax5Percent() {
        this.value = new BigDecimal(0.05);
    }

    public BigDecimal getVuaulue() {
        return value;
    }

    Money calculateFor(Money money) {
        return money.multiplyBy(value);
    }
}
