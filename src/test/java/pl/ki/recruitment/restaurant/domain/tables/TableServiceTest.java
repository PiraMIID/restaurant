package pl.ki.recruitment.restaurant.domain.tables;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import pl.ki.recruitment.restaurant.domain.localization.Localization;
import pl.ki.recruitment.restaurant.domain.order.OrderChunkDTO;
import pl.ki.recruitment.restaurant.domain.order.OrderChunkService;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.*;
import static pl.ki.recruitment.restaurant.domain.tables.Table.State.*;


@ExtendWith(MockitoExtension.class)
class TableServiceTest {

    @InjectMocks
    private TableService underTest;
    @Mock
    private TableRepository tableRepository;
    @Mock
    private OrderChunkService orderChunkService;
    @Mock
    private Localization localization;


    @Test
    void shouldCreateTable() {
        //given
        Table table = mock(Table.class);
        table.setLocalization(localization);

        //when
        when(tableRepository.create(any(Table.class))).thenReturn(table);

        when(tableRepository.checkIsNotAlreadyExist(localization, table.getRoomId(), table.getPositionId()))
                .thenReturn(true)
                .thenReturn(false);
        Table tableCreated = underTest.createTable(localization, anyInt(), anyInt(), anyInt());

        //then

        // ok
        assertEquals(table, tableCreated);

        // throw when already exist
        assertThrows(TableAlredyExistException.class, () -> underTest.createTable(localization, anyInt(), anyInt(), anyInt()));

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
//        System.out.println(table.getState());
//        System.out.println(table.getState());
//        System.out.println(table.getState());


        // Then

        //ok
        assertDoesNotThrow(() -> underTest.closeTable(table.getId()));

        // ok
        assertDoesNotThrow(() -> underTest.closeTable(table.getId()));

        // throw when open
        assertThrows(CannotCloseTableException.class, () -> underTest.closeTable(table.getId()));

    }

    @Test
    void shouldTrowWhenCreateTableAlreadyExist() {
        //given
        //when
        when(tableRepository.checkIsNotAlreadyExist(localization, 1, 1))
                .thenReturn(false)
                .thenReturn(true);
        // then
        assertThrows(TableAlredyExistException.class, () -> underTest.createTable(localization, 1, 1, 1));
        assertDoesNotThrow(() -> underTest.createTable(localization, 1, 1, 1));

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


}
