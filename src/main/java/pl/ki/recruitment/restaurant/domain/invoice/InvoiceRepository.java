package pl.ki.recruitment.restaurant.domain.invoice;

import pl.ki.recruitment.restaurant.domain.shared.kernel.InvoiceId;

interface InvoiceRepository {

    InvoiceId save(Invoice invoice);
}
