//package pl.ki.recruitment.restaurant.domain.invoice;
//
//import org.junit.jupiter.api.Test;
//import pl.ki.recruitment.restaurant.domain.item.Item;
//import pl.ki.recruitment.restaurant.domain.item.Money;
//import pl.ki.recruitment.restaurant.domain.item.Tax10Percent;
//import pl.ki.recruitment.restaurant.domain.item.money.Tax5Percent;
//import pl.ki.recruitment.restaurant.domain.shared.kernel.ItemId;
//import pl.ki.recruitment.restaurant.domain.order.OrderChunkDTO;
//
//import java.math.BigDecimal;
//import java.text.DecimalFormat;
//import java.util.HashSet;
//import java.util.Set;
//
//import static org.junit.jupiter.api.Assertions.assertEquals;
//
//class InvoiceTest {
//
//    private final MockItemService itemService = new MockItemService();
//    private final Set<OrderChunkDTO> orderChunks = new HashSet<>();
//    private Invoice result;
//
//    @Test
//    public void test1() {
//        //given
//        item("i1").withPrice(10.0).withTax5Perc().create();
//        item("i2").withPrice(8.0).withTax10Perc().create();
//
//        orderChunk().with("i1", 1).with("i2", 2).add();
//
//        //when
//        calculate();
//
//        //then
//        assertTotalPriceIs(26.0);
//        assertTotalTaxIs(2.1);
//    }
//
//    @Test
//    public void test2() {
//        //given
//        item("i1").withPrice(10.0).withTax5Perc().create();
//        item("i2").withPrice(8.0).withTax10Perc().create();
//
//        orderChunk().with("i1", 1).with("i2", 2).add();
//        orderChunk().with("i1", 2).with("i2", 1).add();
//
//        //when
//        calculate();
//
//        //then
//        assertTotalPriceIs(54.0);
//        assertEquals(new Money(new BigDecimal(3.9)), result.getTotalTax());
//    }
//
//    @Test
//    public void test3() {
//        //given
//        item("i1").withPrice(10.0).withTax5Perc().create();
//        item("i2").withPrice(8.0).withTax10Perc().create();
//
//        orderChunk().with("i1", 1).with("i2", 2).add();
//        orderChunk().with("i1", 2).with("i2", 1).add();
//        calculate();
//        clearChunks();
//
//        orderChunk().with("i1", 2).with("i2", 2).add();
//
//        //when
//        calculate();
//
//        //then
//        assertTotalPriceIs(90.0);
//        assertEquals(new Money(new BigDecimal(6.5)), result.getTotalTax());
//    }
//
//    private void assertTotalPriceIs(double expectedTotalPrice) {
//        assertEquals(new Money(new BigDecimal(expectedTotalPrice)), result.getTotalPrice());
//    }
//
//    private void assertTotalTaxIs(double expectedTotalTax) {
//        assertEquals(new Money(new BigDecimal(expectedTotalTax)), result.getTotalTax());
//    }
//
//    private ItemAssembler item(String itemId) {
//        return new ItemAssembler(itemId, itemService);
//    }
//
//    private AddOrderAssembler orderChunk() {
//        return new AddOrderAssembler(orderChunks);
//    }
//
//    private void calculate() {
//        if (result == null) {
//            result = new Invoice();
//        }
//        result.add(orderChunks, itemService);
//    }
//
//    private void clearChunks() {
//        orderChunks.clear();
//    }
//
//    private class ItemAssembler {
//
//        private final ItemId itemId;
//        private final MockItemService itemService;
//        private Money price;
//        private Object tax;
//
//        public ItemAssembler(String itemId, MockItemService itemService) {
//            this.itemId = new ItemId(itemId);
//            this.itemService = itemService;
//        }
//
//        public ItemAssembler withPrice(double price) {
//            this.price = new Money(new BigDecimal(price));
//            return this;
//        }
//
//        public ItemAssembler withTax5Perc() {
//            this.tax = new Tax5Percent();
//            return this;
//        }
//
//        public ItemAssembler withTax10Perc() {
//            this.tax = new Tax10Percent();
//            return this;
//        }
//
//        public void create() {
//            itemService.put(itemId, new Item(price, tax));
//        }
//    }
//
//    private class AddOrderAssembler {
//
//        private final Set<OrderChunkDTO> orderChunks;
//        private final OrderChunkDTO orderChunk = new OrderChunkDTO();
//
//        public AddOrderAssembler(Set<OrderChunkDTO> orderChunks) {
//            this.orderChunks = orderChunks;
//        }
//
//        public AddOrderAssembler with(String itemId, int pieces) {
//            orderChunk.addItem(new ItemId(itemId), pieces);
//            return this;
//        }
//
//        public void add() {
//            orderChunks.add(orderChunk);
//        }
//    }
//
//
//    @Test
//    void name() {
//        Decimal decimal = new Decimal();
//        Double.
//        decimal
//    }
//}