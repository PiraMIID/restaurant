package pl.ki.recruitment.restaurant.domain.invoice;

import pl.ki.recruitment.restaurant.domain.item.ItemService;
import pl.ki.recruitment.restaurant.domain.tables.OrderChunkDTO;
import pl.ki.recruitment.restaurant.domain.tables.Table;
import pl.ki.recruitment.restaurant.domain.tables.TableService;

import java.math.BigDecimal;
import java.util.Map;


public class InvoiceService {
    private final InvoiceRepository invoiceRepository;
    private final ItemService itemService;
    private final TableService tableService;

    InvoiceService(InvoiceRepository invoiceRepository, ItemService itemService, TableService tableService) {
        this.invoiceRepository = invoiceRepository;
        this.itemService = itemService;
        this.tableService = tableService;
    }

    public Invoice createForTable(Long tableId) {
        Table table = tableService.findById(tableId);
        Invoice invoice = calculateInvoice(table);
        return invoiceRepository.save(invoice);
    }

    private Invoice calculateInvoice(Table table) {
        return table.getOrderChunks()
                .stream().map(OrderChunkDTO::getItems)
                .map(order -> {
                    BigDecimal totalPrice = BigDecimal.ZERO;
                    BigDecimal totalTax = BigDecimal.ZERO;
                    for (Map.Entry<Long, Integer> entry : order.entrySet()) {
                        Long key = entry.getKey();
                        Integer value = entry.getValue();
                        totalPrice = totalPrice.add(itemService.calculatePrice(key, value));
                        totalTax = totalTax.add(itemService.calculateTax(key, value));
                    }
                    return new Invoice(table.getId(), totalPrice, totalTax);
                }).findFirst().get();
    }

    Invoice getById(Long invoiceId) {
        return invoiceRepository.get(invoiceId).orElseThrow(InvoiceNotExistException::new);
    }

}
