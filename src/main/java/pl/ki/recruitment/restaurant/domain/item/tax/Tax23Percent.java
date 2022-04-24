package pl.ki.recruitment.restaurant.domain.item.tax;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class Tax23Percent implements Tax {

    private final BigDecimal value;

    Tax23Percent() {
        this.value = new BigDecimal("0.23").setScale(2, RoundingMode.HALF_EVEN);
    }

    public BigDecimal getValue() {
        return null;
    }

    public BigDecimal calculateFor(BigDecimal money) {
        return value.multiply(money).setScale(2, RoundingMode.HALF_EVEN);
    }
}
