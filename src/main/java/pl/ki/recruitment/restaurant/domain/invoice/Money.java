package pl.ki.recruitment.restaurant.domain.invoice;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Objects;

class Money {

    private final BigDecimal value;

    public Money(BigDecimal value) {
        value = value.setScale(2, RoundingMode.HALF_EVEN);
        this.value = value;
    }

    public Money add(Money money) {
        return new Money(value.add(money.value));
    }

    public Money multiplyBy(BigDecimal multiplier) {
        return new Money(this.value.multiply(multiplier));
    }

    public Money multiplyBy(Integer value) {
        return new Money(this.value.multiply(new BigDecimal(value)));
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
        return true;

        if (o == null || getClass() != o.getClass())
        return false;

        Money money = (Money) o;
        return value.equals(money.value);
    }

    @Override
    public int hashCode() {
        return Objects.hash(value);
    }

    @Override
    public String toString() {
        return "Money{" +
                "value=" + value +
                '}';
    }
}
