package pl.ki.recruitment.restaurant.domain.invoice;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class InvoiceServiceTest {

    @Mock
    InvoiceRepository invoiceRepository;
    @InjectMocks
    InvoiceService underTest;

    @Test
    void shouldGetInvoiceOrThrowWhenNotExist() {
        // Given
        Invoice invoice = mock(Invoice.class);
        Optional<Invoice> optionalInvoice = Optional.of(invoice);

        // When
        when(invoiceRepository.get(anyLong()))
                .thenReturn(optionalInvoice)
                .thenReturn(Optional.empty());

        // Then
        Assertions.assertEquals(invoice, underTest.getById(anyLong()));
        Assertions.assertThrows(InvoiceNotFoundException.class, () -> underTest.getById(anyLong()));
    }
}