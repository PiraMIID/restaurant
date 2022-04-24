package pl.ki.recruitment.restaurant.domain.tables;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.junit.jupiter.MockitoSettings;
import org.mockito.quality.Strictness;
import pl.ki.recruitment.restaurant.domain.invoice.InvoiceService;
import pl.ki.recruitment.restaurant.domain.localization.Localization;
import pl.ki.recruitment.restaurant.domain.localization.LocalizationService;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.*;
import static pl.ki.recruitment.restaurant.domain.tables.Table.State.*;

@MockitoSettings(strictness = Strictness.STRICT_STUBS)
@ExtendWith(MockitoExtension.class)
class TableServiceTest {

    @Mock
    LocalizationService localizationService;
    @Mock
    InvoiceService invoiceService;
    @Mock
    private TableRepository tableRepository;
    @InjectMocks
    private TableService underTest;
    @Mock
    private Localization localization;


    @Test
    void shouldCreateTable() {
        //given
        Table table = mock(Table.class);
        table.setLocalization(localization);

        //when
        when(tableRepository.checkIsNotAlreadyExist(anyLong(), anyLong(), anyLong()))
                .thenReturn(true)
                .thenReturn(false);


        when(localizationService.getById(anyLong())).thenReturn(localization);

        when(tableRepository.create(any(Table.class))).thenReturn(table);
        Table tableResult = underTest.createTable(localization.getId(),table.getRoomId(), table.getPositionId(), anyInt());

        //then

        // ok
        assertEquals(table, tableResult);

        // throw when already exist
        assertThrows(TableAlreadyExistException.class, () -> underTest.createTable(localization.getId(),table.getRoomId(), table.getPositionId(), anyInt()));

    }

    @Test
    void shouldSaveTable() {
        // Given
        Table table = mock(Table.class);
        // When
        when(tableRepository.save(table)).thenReturn(table);
        // Then
        assertEquals(table, underTest.save(table));
    }

    @Test
    void shouldOpenTable() {
        // Given
        Table table = mock(Table.class);

        when(table.getState())
                .thenReturn(CLOSED)
                .thenReturn(BOOKED)
                .thenReturn(OPEN);

        Optional<Table> tableOptional = Optional.of(table);

        // When
        when(tableRepository.findById(anyLong())).thenReturn(tableOptional);

        // Then

        //ok
        assertDoesNotThrow(() -> underTest.openTable(table.getId()));

        // ok
        assertDoesNotThrow(() -> underTest.openTable(table.getId()));

        // throw when already open
        assertThrows(CannotOpenTableException.class, () -> underTest.openTable(table.getId()), "Table is booked.");

        reset(table);
    }

    @Test
    void shouldBookTable() {
        // Given
        Table table = mock(Table.class);

        when(table.getState())
                .thenReturn(BOOKED)
                .thenReturn(CLOSED)
                .thenReturn(OPEN);

        Optional<Table> tableOptional = Optional.of(table);

        // When
        when(tableRepository.findById(anyLong())).thenReturn(tableOptional);

        // Then

        //ok
        assertDoesNotThrow(() -> underTest.bookTable(table.getId()));

        // ok
        assertDoesNotThrow(() -> underTest.bookTable(table.getId()));

        // throw when open
        assertThrows(CannotBookTableException.class, () -> underTest.bookTable(table.getId()));

    }

    @Test
    void shouldCloseTable() {
        // Given
        Table table = mock(Table.class);

        when(table.getState())
                .thenReturn(BOOKED)
                .thenReturn(OPEN)
                .thenReturn(CLOSED);

        Optional<Table> tableOptional = Optional.of(table);

        // When
        when(tableRepository.findById(anyLong())).thenReturn(tableOptional);

        // Then

        //ok
        assertDoesNotThrow(() -> underTest.closeTable(table.getId()));

        // ok
        assertDoesNotThrow(() -> underTest.closeTable(table.getId()));

        // throw when open
        assertThrows(CannotCloseTableException.class, () -> underTest.closeTable(table.getId()));

    }


    @Test
    void shouldAddOrderChunkToList() {
        // Given
        Table table = spy(Table.class);
        OrderChunkDTO orderChunkDTO = mock(OrderChunkDTO.class);
        List<OrderChunkDTO> orderChunkDTOS = new ArrayList<>();
        orderChunkDTOS.add(orderChunkDTO);
        table.setOrderChunks(orderChunkDTOS);
        table.setId(0L);
        Optional<Table> tableOptional = Optional.of(table);

        // When
        when(tableRepository.findById(table.getId())).thenReturn(tableOptional);

        int sizeOrderChunkBeforeAdd = table.getOrderChunks().size();
        underTest.addOrderChunk(table.getId(), orderChunkDTO);
        int sizeOrderChunkAfterAdd = table.getOrderChunks().size();

        // Then
        assertEquals(sizeOrderChunkBeforeAdd + 1, sizeOrderChunkAfterAdd);


    }

    @Test
    void shouldCreateInvoiceWhenTableClosingAndOrderChunksIsNotEmpty() {
        // Given
        Table table = mock(Table.class);
        Optional<Table> optionalTable = Optional.of(table);
        given(tableRepository.findById(anyLong())).willReturn(optionalTable);
        given(table.getState()).willReturn(OPEN);
        LinkedList<OrderChunkDTO> orderChunkDTOS = new LinkedList<>();
        orderChunkDTOS.add(mock(OrderChunkDTO.class));

        // When
        when(table.getOrderChunks()).thenReturn(orderChunkDTOS);
        underTest.closeTable(anyLong());

        // Then
        verify(invoiceService,  only()).createForTable(anyLong());

    }
}
