package pl.ki.recruitment.restaurant.domain.invoice;

import pl.ki.recruitment.restaurant.domain.item.Item;
import pl.ki.recruitment.restaurant.domain.item.ItemService;
import pl.ki.recruitment.restaurant.domain.order.OrderChunkDTO;
import pl.ki.recruitment.restaurant.domain.tables.TableRepository;
import pl.ki.recruitment.restaurant.domain.tables.TableService;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Arrays;


public class InvoiceService {

    private final InvoiceRepository invoiceRepository;
    private final ItemService itemService;
    private final TableService tableService;
    private final TableRepository tableRepository;

    InvoiceService(InvoiceRepository invoiceRepository, ItemService itemService, TableService tableService, TableRepository tableRepository) {
        this.invoiceRepository = invoiceRepository;
        this.itemService = itemService;
        this.tableService = tableService;
        this.tableRepository = tableRepository;
    }

    Invoice create() {
        Invoice invoice = new Invoice();
        return invoiceRepository.create(invoice);
    }

    public Invoice createForTable(Long tableId) {
        BigDecimal bigDecimalZero = BigDecimal.ZERO.setScale(2, RoundingMode.HALF_EVEN);

        BigDecimal[] indexZeroTotalPriceIndexOneTotalTax = {bigDecimalZero, bigDecimalZero};
        tableService.findById(tableId)
                .getOrderChunks()
                .stream()
                .map(OrderChunkDTO::getItems)
                .forEach(longIntegerMap -> {
                    longIntegerMap.forEach((idItem, countItemInOrder) -> {
//                        System.out.println(idItem + " "+ countItemInOrder);
                        Item item = itemService.get(idItem);
//                        System.out.println(idItem + " "+ countItemInOrder);
//                        System.out.println(item);
                        indexZeroTotalPriceIndexOneTotalTax[0] = indexZeroTotalPriceIndexOneTotalTax[0]
                                .add(item.getPrice().multiply(BigDecimal.valueOf(countItemInOrder)));
                        indexZeroTotalPriceIndexOneTotalTax[1] = indexZeroTotalPriceIndexOneTotalTax[1]
                                .add(item.getTax().calculateFor(item.getPrice()).multiply(BigDecimal.valueOf(countItemInOrder))).setScale(2, RoundingMode.HALF_EVEN);
                    });
                });
        System.out.println(Arrays.toString(Arrays.stream(indexZeroTotalPriceIndexOneTotalTax).toArray()));
        return invoiceRepository.create(tableId, indexZeroTotalPriceIndexOneTotalTax[0], indexZeroTotalPriceIndexOneTotalTax[1]);
    }

    private Invoice getById(Invoice invoice) {
        return invoiceRepository.get(invoice).orElseThrow(InvoiceNotExistException::new);
    }

}
