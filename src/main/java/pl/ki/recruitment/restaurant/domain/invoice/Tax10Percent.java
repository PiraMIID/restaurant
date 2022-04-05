package pl.ki.recruitment.restaurant.domain.invoice;

import java.math.BigDecimal;

class Tax10Percent implements Tax {

    private final BigDecimal value;

    Tax10Percent() {
        this.value = new BigDecimal(0.1);
    }

    public BigDecimal getVuaulue() {
        return value;
    }

    Money calculateFor(Money money) {
        return money.multiplyBy(value);
    }
}
