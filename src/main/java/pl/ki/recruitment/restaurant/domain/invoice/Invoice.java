package pl.ki.recruitment.restaurant.domain.invoice;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Objects;

public class Invoice {

    private Long id;
    private BigDecimal totalPrice;
    private BigDecimal totalTax;


    public Invoice() {
        this.totalPrice = new BigDecimal(0).setScale(2, RoundingMode.HALF_EVEN);
        this.totalTax = new BigDecimal(0).setScale(2, RoundingMode.HALF_EVEN);
    }

    public Invoice(Long id, BigDecimal totalPrice, BigDecimal totalTax) {
        this.id = id;
        this.totalPrice = totalPrice.setScale(2, RoundingMode.HALF_EVEN);
        this.totalTax = totalTax.setScale(2, RoundingMode.HALF_EVEN);
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public BigDecimal getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(BigDecimal totalPrice) {
        this.totalPrice = totalPrice;
    }

    public BigDecimal getTotalTax() {
        return totalTax;
    }

    public void setTotalTax(BigDecimal totalTax) {
        this.totalTax = totalTax;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Invoice invoice = (Invoice) o;
        return Objects.equals(id, invoice.id) && Objects.equals(totalPrice, invoice.totalPrice) && Objects.equals(totalTax, invoice.totalTax);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, totalPrice, totalTax);
    }

    @Override
    public String toString() {
        return "Invoice{" +
                "id=" + id +
                ", totalPrice=" + totalPrice +
                ", totalTax=" + totalTax +
                '}';
    }
}
