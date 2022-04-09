package pl.ki.recruitment.restaurant.domain.tables;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import pl.ki.recruitment.restaurant.domain.localization.Localization;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.when;


@ExtendWith(MockitoExtension.class)
class TableServiceTest {

//    private final MockTableRepository willGiveNameLAtterrr = new MockTableRepository();
//    @InjectMocks
//    private InvoiceService InvoiceService;

    @Captor
    ArgumentCaptor<Table> captor;
    @InjectMocks
    private TableService underTest;
    @Mock
    private TableRepository tableRepository;
    @Mock
    private Localization localization;

    @Test
    public void shouldCreateTable() {
        //given
        given(tableRepository.save(any(Table.class))).willReturn(captor.capture());
        //when
        Table table = underTest.createTable(any(), any(), any(), any());
        //then
        assertEquals(captor.getValue(), table);

        //given

        //when
//        createTable("Rome", "Room 1", "10");
//
//        //then
//        assertTablesCountIs(1);
//        assertTableExists("Rome", "Room 1", "10");
    }


    @Test
    public void testCreateTheSame() {
        //given
        when(tableRepository.checkIsNotAlreadyExist(any(Localization.class), anyInt(), anyInt())).thenReturn(false);
        //when
        //then
        assertThrows(TableAlredyExistException.class, () -> underTest.createTable(localization, 1, 1, 1));

    }

}
