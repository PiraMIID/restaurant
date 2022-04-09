package pl.ki.recruitment.restaurant.domain.item;

import pl.ki.recruitment.restaurant.domain.item.money.Tax;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Objects;

public class Item {

    private Long id;
    private BigDecimal price;
    private Tax tax;

    {
        price.setScale(2, RoundingMode.HALF_EVEN);
    }


    Item(BigDecimal price, Tax tax) {
        this.price = price;
        this.tax = tax;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public Tax getTax() {
        return tax;
    }

    public void setTax(Tax tax) {
        this.tax = tax;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Item item = (Item) o;
        return Objects.equals(id, item.id) && Objects.equals(price, item.price) && Objects.equals(tax, item.tax);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, price, tax);
    }
}
