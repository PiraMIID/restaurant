package pl.ki.recruitment.restaurant.domain.tables;

import java.util.Set;
import pl.ki.recruitment.restaurant.domain.shared.kernel.OrderChunkDTO;

interface InvoiceService {

    void createInvoiceFor(TableId tableId, Set<OrderChunkDTO> orderChunks);
}
