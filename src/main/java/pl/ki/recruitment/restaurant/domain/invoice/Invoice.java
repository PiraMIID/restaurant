package pl.ki.recruitment.restaurant.domain.invoice;

import java.math.BigDecimal;
import java.util.Objects;

class Invoice {

    private Long Long;
    private BigDecimal totalPrice;
    private BigDecimal totalTax;

    public Invoice() {
    }

    public Invoice(Long Long, BigDecimal totalPrice, BigDecimal totalTax) {
        this.Long = Long;
        this.totalPrice = totalPrice;
        this.totalTax = totalTax;
    }

    public Long getLong() {
        return Long;
    }

    public void setLong(Long Long) {
        this.Long = Long;
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
        return Objects.equals(Long, invoice.Long) && Objects.equals(totalPrice, invoice.totalPrice) && Objects.equals(totalTax, invoice.totalTax);
    }

    @Override
    public int hashCode() {
        return Objects.hash(Long, totalPrice, totalTax);
    }
}
