package pl.ki.recruitment.restaurant.domain.tables;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import pl.ki.recruitment.restaurant.domain.shared.kernel.OrderChunkDTO;

class MockInvoiceService implements InvoiceService {

    private Map<TableId, Set<OrderChunkDTO>> invoicesDataPerTable = new HashMap<>();

    @Override
    public void createInvoiceFor(TableId tableId, Set<OrderChunkDTO> orderChunks) {
        invoicesDataPerTable.put(tableId, orderChunks);
    }

    boolean hasInvoiceFor(TableId tableId) {
        return invoicesDataPerTable.containsKey(tableId);
    }

    boolean hasInvoiceFor(TableId tableId, Set<OrderChunkDTO> orderChunks) {
        return invoicesDataPerTable.containsKey(tableId) && invoicesDataPerTable.get(tableId).equals(orderChunks);
    }
}
