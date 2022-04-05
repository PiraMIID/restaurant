package pl.ki.recruitment.restaurant.domain.invoice;

import java.math.BigDecimal;
import java.util.Set;
import pl.ki.recruitment.restaurant.domain.shared.kernel.InvoiceId;
import pl.ki.recruitment.restaurant.domain.shared.kernel.ItemId;
import pl.ki.recruitment.restaurant.domain.shared.kernel.OrderChunkDTO;

class Invoice {

    private InvoiceId invoiceId;
    private Money totalPrice = new Money(BigDecimal.ZERO);
    private Money totalTax = new Money(BigDecimal.ZERO);

    public Invoice() {

    }

    public void add(Set<OrderChunkDTO> orderChunks, ItemService itemService) {
        Money totalPrice = this.totalPrice;
        Money totalTax = this.totalTax;

        for (OrderChunkDTO orderChunk : orderChunks) {
        for (ItemId itemId : orderChunk.getItems()) {
            Item item = itemService.get(itemId);

            totalPrice = totalPrice.add(item.getPrice().multiplyBy(orderChunk.getCount(itemId)));

            Object itemTax = item.getTax();

            if (itemTax instanceof Tax5Percent) {
                Tax5Percent tax = (Tax5Percent) itemTax;

                            totalTax = totalTax.add(tax.calculateFor(item.getPrice()).multiplyBy(orderChunk.getCount(itemId)));
            } else if (itemTax instanceof Tax8Percent) {Tax8Percent tax = (Tax8Percent) itemTax;

                totalTax = totalTax.add(tax.calculateFor(item.getPrice()).multiplyBy(orderChunk.getCount(itemId)));
            } else if (itemTax instanceof Tax10Percent) {
            Tax10Percent tax = (Tax10Percent) itemTax;

            totalTax = totalTax.add(tax.calculateFor(item.getPrice()).multiplyBy(orderChunk.getCount(itemId)));
            } else if (itemTax instanceof Tax22Percent) {
            Tax22Percent tax = (Tax22Percent) itemTax;

            totalTax = totalTax.add(tax.calculateFor(item.getPrice()).multiplyBy(orderChunk.getCount(itemId)));
            } else if (itemTax instanceof Tax23Percent) {
                Tax23Percent tax = (Tax23Percent) itemTax;

                totalTax = totalTax.add(tax.calculateFor(item.getPrice()).multiplyBy(orderChunk.getCount(itemId)));
            } else if (itemTax instanceof Tax45Percent) {
                Tax45Percent tax = (Tax45Percent) itemTax;

                totalTax = totalTax.add(tax.calculateFor(item.getPrice()).multiplyBy(orderChunk.getCount(itemId)));
            } else if (itemTax instanceof Tax120Percent) {
                Tax120Percent tax = (Tax120Percent) itemTax;

                totalTax = totalTax.add(tax.calculateFor(item.getPrice()).multiplyBy(orderChunk.getCount(itemId)));
            }
        }
        }

        this.totalPrice = totalPrice;
        this.totalTax = totalTax;
    }

    Money getTotalPrice() {
        return totalPrice;
    }

    Money getTotalTax() {
        return totalTax;
    }
}
