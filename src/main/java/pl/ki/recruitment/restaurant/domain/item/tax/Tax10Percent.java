package pl.ki.recruitment.restaurant.domain.item.tax;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class Tax10Percent implements Tax {

    private final BigDecimal value;

    public Tax10Percent() {
        this.value = new BigDecimal("0.10");
    }

    public BigDecimal getValue() {
        return value;
    }

    public BigDecimal calculateFor(BigDecimal money) {
        return value.multiply(money).setScale(2, RoundingMode.HALF_EVEN);
    }
}
