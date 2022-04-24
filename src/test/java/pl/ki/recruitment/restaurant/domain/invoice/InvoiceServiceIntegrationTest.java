package pl.ki.recruitment.restaurant.domain.invoice;

import org.junit.jupiter.api.Assertions;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import pl.ki.recruitment.restaurant.domain.item.Item;

import pl.ki.recruitment.restaurant.domain.item.ItemService;
import pl.ki.recruitment.restaurant.domain.item.tax.*;
import pl.ki.recruitment.restaurant.domain.tables.OrderChunkDTO;
import pl.ki.recruitment.restaurant.domain.tables.Table;

import pl.ki.recruitment.restaurant.domain.tables.TableService;

import java.math.BigDecimal;
import java.util.LinkedList;
import java.util.List;

import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class InvoiceServiceIntegrationTest {

    @Mock
    InvoiceRepository invoiceRepository;
    @Mock
    private TableService tableService;
    @Mock
    private ItemService itemService;
    @InjectMocks
    private InvoiceService underTest;

    @Test
    void shouldCreateNewInvoice() {
        // Given
        Table mockTable = mock(Table.class);
        Long idTable = 0L;
        OrderChunkDTO orderChunkDTO = new OrderChunkDTO();
        orderChunkDTO.addItem(idTable, 10);
        LinkedList<OrderChunkDTO> orderChunkDTOS = new LinkedList<>();
        orderChunkDTOS.add(orderChunkDTO);
        Item item = new Item(new BigDecimal(10), spy(Tax5Percent.class));

//         When
        when(tableService.findById(idTable)).thenReturn(mockTable);
        when(itemService.get(anyLong())).thenReturn(item);
        when(itemService.calculatePrice(anyLong(), anyInt())).thenCallRealMethod();
        when(itemService.calculateTax(anyLong(), anyInt())).thenCallRealMethod();

        when(mockTable.getOrderChunks()).thenReturn(orderChunkDTOS);

        underTest.createForTable(idTable);

        ArgumentCaptor<Invoice> invoiceArgumentCaptor = ArgumentCaptor.forClass(Invoice.class);
        verify(invoiceRepository).save(invoiceArgumentCaptor.capture());

        // Then
        Invoice invoice = invoiceArgumentCaptor.getValue();
        BigDecimal exceptedPrice = new BigDecimal("100.00");
        BigDecimal exceptedTax = new BigDecimal("5.00");
        Assertions.assertEquals(exceptedPrice, invoice.getTotalPrice());
        Assertions.assertEquals(exceptedTax, invoice.getTotalTax());

    }

    @Test
    void shouldCreateNewInvoiceAndCalculateProperlyTaxByItem() {
        // Given
        Table mockTable = mock(Table.class);
        LinkedList<OrderChunkDTO> orderChunkDTOS = new LinkedList<>();
        for (int i = 0; i < 6; i++) {
            OrderChunkDTO orderChunkDTO1 = new OrderChunkDTO();
            orderChunkDTO1.addItem((long) i,10);
            orderChunkDTOS.add(orderChunkDTO1);
        }
//         When
        when(tableService.findById(anyLong())).thenReturn(mockTable);
        when(mockTable.getOrderChunks()).thenReturn(orderChunkDTOS);
        when(itemService.get(anyLong()))
                .thenReturn(new Item(new BigDecimal(10), spy(Tax5Percent.class)))
                .thenReturn(new Item(new BigDecimal(10), spy(Tax5Percent.class)))
                .thenReturn(new Item(new BigDecimal(10), spy(Tax8Percent.class)))
                .thenReturn(new Item(new BigDecimal(10), spy(Tax8Percent.class)))
                .thenReturn(new Item(new BigDecimal(10), spy(Tax10Percent.class)))
                .thenReturn(new Item(new BigDecimal(10), spy(Tax10Percent.class)))
                .thenReturn(new Item(new BigDecimal(10), spy(Tax22Percent.class)))
                .thenReturn(new Item(new BigDecimal(10), spy(Tax22Percent.class)))
                .thenReturn(new Item(new BigDecimal(10), spy(Tax23Percent.class)))
                .thenReturn(new Item(new BigDecimal(10), spy(Tax23Percent.class)))
                .thenReturn(new Item(new BigDecimal(10), spy(Tax45Percent.class)))
                .thenReturn(new Item(new BigDecimal(10), spy(Tax45Percent.class)));
        when(itemService.calculatePrice(anyLong(), anyInt())).thenCallRealMethod();
        when(itemService.calculateTax(anyLong(), anyInt())).thenCallRealMethod();
        for (int i = 0; i < 6; i++) {
            underTest.createForTable((long) i);
        }

        ArgumentCaptor<Invoice> invoiceArgumentCaptor = ArgumentCaptor.forClass(Invoice.class);

        verify(invoiceRepository, times(6)).save(invoiceArgumentCaptor.capture());

        // Then
        List<Invoice> allValues = invoiceArgumentCaptor.getAllValues();

        Assertions.assertEquals(new BigDecimal("5.00"), allValues.get(0).getTotalTax());
        Assertions.assertEquals(new BigDecimal("8.00"), allValues.get(1).getTotalTax());
        Assertions.assertEquals(new BigDecimal("10.00"), allValues.get(2).getTotalTax());
        Assertions.assertEquals(new BigDecimal("22.00"), allValues.get(3).getTotalTax());
        Assertions.assertEquals(new BigDecimal("23.00"), allValues.get(4).getTotalTax());
        Assertions.assertEquals(new BigDecimal("45.00"), allValues.get(5).getTotalTax());

    }

}