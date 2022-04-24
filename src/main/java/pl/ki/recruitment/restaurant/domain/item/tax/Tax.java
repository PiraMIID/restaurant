package pl.ki.recruitment.restaurant.domain.item.tax;

import java.math.BigDecimal;

public interface Tax {

    BigDecimal getValue();

    BigDecimal calculateFor(BigDecimal money);
}
