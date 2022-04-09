package pl.ki.recruitment.restaurant.domain.invoice;

import java.util.Optional;

interface InvoiceRepository {

    Invoice save(Invoice invoice);

    Optional<Invoice> get(Long Long);
}
