package pl.ki.recruitment.restaurant.domain.tables;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import org.junit.jupiter.api.Test;
import pl.ki.recruitment.restaurant.domain.shared.kernel.ItemId;
import pl.ki.recruitment.restaurant.domain.shared.kernel.OrderChunkDTO;
import pl.ki.recruitment.restaurant.domain.tables.Table.State;

class TableTest {

    private Map<TableId, Table> justMap = new HashMap<>();
    private MockInvoiceService mockInvoiceService = new MockInvoiceService();

    @Test
    public void testOpenTable() {
        //given
        table("T1");

        //when
        openTable("T1");

        //then
        assertTableIsOpen("T1");
    }

    @Test
    public void testCannotOpenAlreadyOpenTable() {
        //given
        table("T1");
        openTable("T1");

        //when
        assertThrows(CannotOpenTableException.class, () -> {
            openTable("T1");
        });

        //then
        assertTableIsOpen("T1");
    }

    @Test
    public void testOpenBookedTable() {
        //given
        TableId tId = new TableId("T1");
        Table table = new Table(new LocalizationId("l"), "r", "p", new PlacesCount(10));
        table.setId(tId);
        justMap.put(tId, table);
        justMap.get(new TableId("T1")).book();

        //when
        justMap.get(new TableId("T1")).open();

        //then
        table = getTable("T1");

        assertEquals(State.OPEN, table.getState());
    }

    @Test
    public void testAddOrderChunk() {
        //given
        table("T1");
        openTable("T1");

        //when
        addOrderChunkTo("T1").with("Soup1", 2).add();

        //then
        assertTable("T1").hasOrderChunkCount(1);
        assertTable("T1").hasOrderChunkWith("Soup1", 2);
    }

    @Test
    public void testAddOrderChunk2() {
        //given
        table("T1");
        openTable("T1");

        //when
        addOrderChunkTo("T1").with("Soup1", 2).add();
        addOrderChunkTo("T1").with("Dish3", 1).add();
        addOrderChunkTo("T1").with("Dish4", 1).add();

        //then
        assertTable("T1").hasOrderChunkCount(3);
        assertTable("T1").hasOrderChunkWith("Soup1", 2);
        assertTable("T1").hasOrderChunkWith("Dish3", 1);
        assertTable("T1").hasOrderChunkWith("Dish4", 1);
    }

    @Test
    public void testAddOrderChunkOnClosedTable() {
        //given
        table("T1");

        //when
        assertThrows(cannotaddorderchunkException.class, () -> {
            addOrderChunkTo("T1").with("Soup1", 2).add();
        });

        //then
    }

    @Test
    public void testAddOrderChunkOnClosedBookedTable() {
        //given
        table("T1");
        bookTable("T1");

        //when
        assertThrows(cannotaddorderchunkException.class, () -> {
            addOrderChunkTo("T1").with("Soup1", 2).add();
        });

        //then
    }

    @Test
    public void testCloseOpenTableWithoutOrders() {
        //given
        table("T1");
        openTable("T1");

        //when
        closeTable("T1");

        //then
        assertTableIsClosed("T1");
        assertInvoiceFor("T1").isNotCreated();
    }

    @Test
    public void testCloseOpenTable() {
        //given
        table("T1");
        openTable("T1");
        addOrderChunkTo("T1").with("Soup1", 2).add();
        addOrderChunkTo("T1").with("Dish3", 1).add();
        addOrderChunkTo("T1").with("Dish4", 1).add();

        //when
        closeTable("T1");

        //then
        assertTableIsClosed("T1");
        assertInvoiceFor("T1")
                .withOrderChunk("Soup1", 2)
                .withOrderChunk("Dish3", 1)
                .withOrderChunk("Dish4", 1)
                .isCreated();
    }

    @Test
    public void testCloseBookedTable() {
        //given
        table("T1");
        bookTable("T1");

        //when
        closeTable("T1");

        //then
        assertTableIsClosed("T1");
    }

    @Test
    public void testClosingTableDoesntInterfere() {
        //given
        table("T1");
        table("T2");
        openTable("T1");
        openTable("T2");

        //when
        closeTable("T2");

        //then
        assertTableIsOpen("T1");
        assertTableIsClosed("T2");
    }

    @Test
    public void testCannotCloseNotOpenTable() {
        //given
        table("T1");

        //when
        assertThrows(cannotclosetableexception.class, () -> {
            closeTable("T1");
        });

        //then
        assertTableIsClosed("T1");
    }

    @Test
    public void testCannotCloseAlreadyClosedTable() {
        //given
        table("T1");
        openTable("T1");
        closeTable("T1");

        //when
        assertThrows(cannotclosetableexception.class, () -> {
            closeTable("T1");
        });

        //then
        assertTableIsClosed("T1");
    }

    @Test
    public void testBookTable() {
        //given
        table("T1");

        //when
        bookTable("T1");

        //then
        assertTableIsBooked("T1");
    }

    @Test
    public void testCannotBookOpenTable() {
        //given
        table("T1");
        openTable("T1");

        //when
        assertThrows(CannotBookTableException.class, () -> {
            bookTable("T1");
        });

        //then
        assertTableIsOpen("T1");
    }

    private void table(String tableId) {
        TableId tId = new TableId(tableId);
        Table table = new Table(new LocalizationId("l"), "r", "p", new PlacesCount(10));
        table.setId(tId);
        justMap.put(tId, table);
    }

    private void openTable(String tableId) {
        getTable(tableId).open();
    }

    private AddOrderAssembler addOrderChunkTo(String tableId) {
        Table table = getTable(tableId);

        return new AddOrderAssembler(table);
    }

    private void closeTable(String tableId) {
        getTable(tableId).close(mockInvoiceService);
    }

    private void bookTable(String tableId) {
        getTable(tableId).book();
    }

    private void assertTableIsOpen(String tableId) {
        Table table = getTable(tableId);

        assertEquals(State.OPEN, table.getState());
    }

    private AssertTableAssembler assertTable(String tableId) {
        Table table = getTable(tableId);

        return new AssertTableAssembler(table);
    }

    private void assertTableIsClosed(String tableId) {
        Table table = getTable(tableId);

        assertEquals(State.CLOSED, table.getState());
    }

    private void assertTableIsBooked(String tableId) {
        Table table = getTable(tableId);

        assertEquals(State.BOOKED, table.getState());
    }

    private AssertInvoiceAssembler assertInvoiceFor(String tableId) {
        return new AssertInvoiceAssembler(new TableId(tableId), mockInvoiceService);
    }

    private Table getTable(String tableId) {
        return justMap.get(new TableId(tableId));
    }

    private class AddOrderAssembler {

        private Table table;
        private OrderChunkDTO orderChunk = new OrderChunkDTO();

        public AddOrderAssembler(Table table) {
            this.table = table;
        }

        public AddOrderAssembler with(String itemId, int pieces) {
            orderChunk.addItem(new ItemId(itemId), pieces);
            return this;
        }

        public void add() {
            table.addOrderChunk(orderChunk);
        }
    }

    private class AssertTableAssembler {

        private Table table;

        public AssertTableAssembler(Table table) {
            this.table = table;
        }

        public void hasOrderChunkWith(String itemId, int pieces) {
            OrderChunkDTO orderChunkDTO = new OrderChunkDTO();
            orderChunkDTO.addItem(new ItemId(itemId), pieces);

            assertTrue(table.hasOrderChunk(orderChunkDTO));
        }

        public void hasOrderChunkCount(int count) {
            assertTrue(table.hasOrderChunkCount(count));
        }
    }

    private class AssertInvoiceAssembler {

        private TableId tableId;
        private MockInvoiceService mockInvoiceService;
        private Set<OrderChunkDTO> orderChunks = new HashSet<>();

        public AssertInvoiceAssembler(TableId tableId, MockInvoiceService mockInvoiceService) {
            this.tableId = tableId;
            this.mockInvoiceService = mockInvoiceService;
        }

        public AssertInvoiceAssembler withOrderChunk(String itemId, int pieces) {
            OrderChunkDTO orderChunkDTO = new OrderChunkDTO();
            orderChunkDTO.addItem(new ItemId(itemId), pieces);

            orderChunks.add(orderChunkDTO);

            return this;
        }

        public void isCreated() {
            assertTrue(mockInvoiceService.hasInvoiceFor(tableId, orderChunks));
        }

        public void isNotCreated() {
            assertFalse(mockInvoiceService.hasInvoiceFor(tableId));
        }
    }
}