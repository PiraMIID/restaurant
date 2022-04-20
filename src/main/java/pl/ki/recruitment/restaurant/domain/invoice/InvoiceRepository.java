package pl.ki.recruitment.restaurant.domain.invoice;

import java.math.BigDecimal;
import java.util.Optional;

interface InvoiceRepository {

    Invoice save(Invoice invoice);

    Optional<Invoice> get(Invoice invoice);

    Invoice create(Invoice invoice);

    Invoice create(Long tableId, BigDecimal indexZeroTotalPriceIndexOneTotalTax, BigDecimal indexZeroTotalPriceIndexOneTotalTax1);
}
