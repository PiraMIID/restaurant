package pl.ki.recruitment.restaurant.domain.tables;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;

class TableServiceTest {

    private MockTableRepository willGiveNameLAtterrr = new MockTableRepository();
    private MockInvoiceService mockInvoiceService = new MockInvoiceService();
    private TableService service = new TableService(willGiveNameLAtterrr, mockInvoiceService);

    @Test
    public void testCreateOne() {
        //given

        //when
        createTable("Rome", "Room 1", "10");

        //then
        assertTablesCountIs(1);
        assertTableExists("Rome", "Room 1", "10");
    }

    @Test
    public void testCreateTwo() {
        //given
        createTable("Venice", "Room 1", "10");

        //when
        createTable("Rome", "Room 4", "2");

        //then
        assertTablesCountIs(2);
        assertTableExists("Venice", "Room 1", "10");
        assertTableExists("Rome", "Room 4", "2");
    }

    @Test
    public void testCreateTwoAgain() {
        //given
        createTable("Rome", "Room 4", "1");

        //when
        createTable("Rome", "Room 4", "2");

        //then
        assertTablesCountIs(2);
        assertTableExists("Rome", "Room 4", "1");
        assertTableExists("Rome", "Room 4", "2");
    }

    @Test
    public void testCreateTheSame() {
        //given
        createTable("Rome", "Room 4", "1");

        //when
        assertThrows(CannotCreateTableException.class, () -> {
            createTable("Rome", "Room 4", "1");
        });

        //then
        assertTablesCountIs(1);
        assertTableExists("Rome", "Room 4", "1");
    }

    private void createTable(String localizationId, String roomId, String positionId) {
        service.createTable(new LocalizationId(localizationId), roomId, positionId,
                            new PlacesCount(10));
    }

    private void assertTablesCountIs(int expectedCount) {
        assertEquals(expectedCount, willGiveNameLAtterrr.getTablesCount());
    }

    private void assertTableExists(String localizationId, String roomId, String positionId) {
        assertNotNull(
                willGiveNameLAtterrr.findBy(new LocalizationId(localizationId), new RoomId(roomId),
                                            new PositionId(positionId)));
    }
}
