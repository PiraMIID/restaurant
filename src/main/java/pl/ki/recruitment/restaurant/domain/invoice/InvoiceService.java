package pl.ki.recruitment.restaurant.domain.invoice;

import pl.ki.recruitment.restaurant.domain.item.ItemService;
import pl.ki.recruitment.restaurant.domain.order.OrderChunkDTO;

import java.util.LinkedList;

public class InvoiceService {

    private final InvoiceRepository invoiceRepository;
    private final ItemService itemService;

    InvoiceService(InvoiceRepository repo, ItemService itemService) {
        this.invoiceRepository = repo;
        this.itemService = itemService;
    }

    Invoice create() {
        Invoice invoice = new Invoice();
        return invoiceRepository.save(invoice);
    }

//    Invoice createWith(LinkedList<OrderChunkDTO> orderChunks) {
//        Invoice invoice = create();
//        orderChunks.forEach(orderChunkDTO -> {
//            itemService.getPrice(orderChunkDTO);
//            itemService.getTax(orderChunkDTO);
//        });
//        invoice.addOrdersChunks(orderChunks);
//    }


    public Invoice addOrdersChunks(Long Long, LinkedList<OrderChunkDTO> orderChunks) {
//        Invoice invoice = getById(Long);
//        for (OrderChunkDTO orderChunk : orderChunks) {
//            orderChunk.getItems().forEach(itemId -> {
//                Item item = itemService.get(itemId);
//                invoice.setTotalPrice(item.getPrice().multiply(BigDecimal.valueOf(orderChunk.getItems().get(item.getId()))));
//
////                                Tax tax = item.getTax();
////                                this.totalTax.add(tax.calculateFor(item.getPrice()).multiply(orderChunk.getCount(itemId)));
//                invoice.setTotalTax(item.getTax().getValue().add(item.getTax().calculateFor(item.getPrice())));
//            });
//        }
        return null;
    }

    private Invoice getById(Long Long) {
        return invoiceRepository.get(Long)
                .orElseThrow(InvoiceNotExistException::new);
    }
}
