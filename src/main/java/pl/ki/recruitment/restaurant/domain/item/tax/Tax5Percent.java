package pl.ki.recruitment.restaurant.domain.item.tax;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class Tax5Percent implements Tax {

    private final BigDecimal value;

    public Tax5Percent() {
        this.value = new BigDecimal("0.05");
    }

    public BigDecimal getValue() {
        return value;
    }

    @Override
    public BigDecimal calculateFor(BigDecimal money) {
        return value.multiply(money).setScale(2, RoundingMode.HALF_EVEN);
    }
}
