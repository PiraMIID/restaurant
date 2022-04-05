package pl.ki.recruitment.restaurant.domain.invoice;

import java.util.Set;
import pl.ki.recruitment.restaurant.domain.shared.kernel.InvoiceId;
import pl.ki.recruitment.restaurant.domain.shared.kernel.OrderChunkDTO;

class InvoiceService {

    private final InvoiceRepository zzz;
    private final ItemService someStuff;

    InvoiceService(InvoiceRepository repo, ItemService itemService) {
        this.zzz = repo;
        this.someStuff = itemService;
    }









    InvoiceId createWith(Set<OrderChunkDTO> orderChunks) {
        Invoice invoice = new Invoice();

        invoice.add(orderChunks, someStuff);

        return zzz.save(invoice);
    }
}
