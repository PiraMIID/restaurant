package pl.ki.recruitment.restaurant.domain.invoice;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import pl.ki.recruitment.restaurant.domain.item.Item;
import pl.ki.recruitment.restaurant.domain.item.ItemRepository;
import pl.ki.recruitment.restaurant.domain.item.ItemService;
import pl.ki.recruitment.restaurant.domain.item.money.Tax5Percent;
import pl.ki.recruitment.restaurant.domain.order.OrderChunkDTO;
import pl.ki.recruitment.restaurant.domain.tables.Table;
import pl.ki.recruitment.restaurant.domain.tables.TableService;

import java.math.BigDecimal;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class InvoiceServiceTest {

    @Mock
    InvoiceRepository invoiceRepository;
    @Mock
    ItemRepository itemRepository;
    @InjectMocks
    private InvoiceService underTest;
    @Mock
    private TableService tableService;
    @Mock
    private ItemService itemService;

    @Test
    void shouldCreateNewInvoice() {
        // Given
        Table mockTable = mock(Table.class);
        Long idTable = 0L;
        Invoice spyInvoice = spy(Invoice.class);
        OrderChunkDTO orderChunkDTO = new OrderChunkDTO();
        orderChunkDTO.addItem(idTable, 10);
        LinkedList<OrderChunkDTO> orderChunkDTOS = new LinkedList<>();
        orderChunkDTOS.add(orderChunkDTO);

        Item item = new Item(new BigDecimal(0), spy(Tax5Percent.class));

        // When
        when(tableService.findById(idTable)).thenReturn(mockTable);
        when(itemRepository.get(anyLong())).thenReturn(Optional.of(item));
        when(itemService.get(anyLong())).thenReturn(item);
        when(mockTable.getOrderChunks()).thenReturn(orderChunkDTOS);

        // Then
        Invoice invoiceFor = underTest.createForTable(idTable);

        verify(invoiceRepository).create(invoiceFor);


    }

    @Test
    void shouldCalculateAndCreateInvoiceToTable() {
        // Given
        Long idTable = 0L;
        Table mockTable = mock(Table.class);
        mockTable.setId(idTable);
//        item mock
        Item item = new Item(BigDecimal.valueOf(11L), new Tax5Percent());
        item.setId(idTable);
//        item.setPrice(BigDecimal.valueOf(11L));
//        mockItem.setTax(new Tax5Percent());
        when(itemService.get(idTable)).thenReturn(item);

        List<OrderChunkDTO> orderChunkDTOS = new LinkedList<>();
//        OrderChunkDTO mockOrderChunkDTO = mock(OrderChunkDTO.class);
        OrderChunkDTO orderChunkDTO = new OrderChunkDTO();
        orderChunkDTO.addItem(idTable, 12);

        orderChunkDTOS.add(orderChunkDTO);

        System.out.println(orderChunkDTO.getItems());

        mockTable.setOrderChunks(orderChunkDTOS);

//        ItemRepository itemRepository = mock(ItemRepository.class);
        Optional<Item> mockItem1 = Optional.of(item);
//        when(itemRepository.get(mockItem.getId())).thenReturn(mockItem1);
//        when(itemService.get(mockItem.getId())).thenReturn(mockItem);
//        when(mockItem.getPrice()).thenReturn(BigDecimal.valueOf(10L));
//        when(mockItem.getTax()).thenReturn(mock(Tax5Percent.class));

        // When
        when(tableService.findById(idTable)).thenReturn(mockTable);
        when(tableService.findById(idTable).getOrderChunks()).thenReturn(orderChunkDTOS);


//        ArgumentCaptor<BigDecimal> bigDecimalArgumentCaptor1 = ArgumentCaptor.forClass(BigDecimal.class);
//        ArgumentCaptor<BigDecimal> bigDecimalArgumentCaptor2 = ArgumentCaptor.forClass(BigDecimal.class);
//        ArgumentCaptor<Invoice> invoiceArgumentCaptor = ArgumentCaptor.forClass(Invoice.class);
//        when(invoiceRepository.create(mockTable.getId(), any(), any())).thenReturn(invoiceArgumentCaptor.capture());
        Invoice forTable = underTest.createForTable(mockTable.getId());

        System.out.println(forTable);
//        System.out.println(mock);
//        bigDecimalArgumentCaptor1.getAllValues().forEach(System.out::println);
//        bigDecimalArgumentCaptor2.getAllValues().forEach(System.out::println);
//
//        when(invoiceRepository.save(any(Invoice.class))).thenReturn(invoiceFor);
//        BigDecimal totalPrice = verify(underTest).createForTable(
//                idTable
//        ).getTotalPrice();
//        System.out.printf(" " + totalPrice);
        // Then
//        System.out.println(invoiceFor);
//        Assertions.assertEquals(BigDecimal.valueOf(200L), invoiceFor.getTotalPrice());

    }
}