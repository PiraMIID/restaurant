package pl.ki.recruitment.restaurant.domain.invoice;

import java.util.List;
import java.util.Optional;

public interface InvoiceRepository {
    Optional<Invoice> get(Long invoice);

    Invoice save(Invoice invoice);

}
