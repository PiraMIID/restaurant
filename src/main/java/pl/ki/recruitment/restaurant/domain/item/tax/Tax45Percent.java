package pl.ki.recruitment.restaurant.domain.item.tax;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class Tax45Percent implements Tax {

    private final BigDecimal value;

    Tax45Percent() {
        this.value = new BigDecimal("0.45").setScale(2, RoundingMode.HALF_EVEN);
    }

    public BigDecimal getValue() {
        return value;
    }

    public BigDecimal calculateFor(BigDecimal money) {
        return value.multiply(money).setScale(2, RoundingMode.HALF_EVEN);
    }
}
