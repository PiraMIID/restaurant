//package pl.ki.recruitment.restaurant.domain.tables;
//
//import pl.ki.recruitment.restaurant.domain.order.OrderChunkDTO;
//
//import java.util.HashMap;
//import java.util.Set;
//
//class MockInvoiceService implements InvoiceService {
//
//    private final Map<TableId, Set<OrderChunkDTO>> invoicesDataPerTable = new HashMap<>();
//
//    @Override
//    public void createInvoiceFor(TableId tableId, Set<OrderChunkDTO> orderChunks) {
//        invoicesDataPerTable.put(tableId, orderChunks);
//    }
//
//    boolean hasInvoiceFor(TableId tableId) {
//        return invoicesDataPerTable.containsKey(tableId);
//    }
//
//    boolean hasInvoiceFor(TableId tableId, Set<OrderChunkDTO> orderChunks) {
//        return invoicesDataPerTable.containsKey(tableId) && invoicesDataPerTable.get(tableId).equals(orderChunks);
//    }
//}
