package pl.ki.recruitment.restaurant.domain.shared.kernel;

import java.util.Objects;

public class InvoiceId {

    private final String invoiceId;

    public InvoiceId(String invoiceId) {
        this.invoiceId = invoiceId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        InvoiceId invoiceId1 = (InvoiceId) o;
        return invoiceId.equals(invoiceId1.invoiceId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(invoiceId);
    }
}
