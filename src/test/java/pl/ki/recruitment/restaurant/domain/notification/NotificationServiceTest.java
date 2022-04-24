package pl.ki.recruitment.restaurant.domain.notification;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import pl.ki.recruitment.restaurant.domain.invoice.Invoice;
import pl.ki.recruitment.restaurant.domain.invoice.InvoiceNotExistException;
import pl.ki.recruitment.restaurant.domain.invoice.InvoiceRepository;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class NotificationServiceTest {

    @Mock
    SubscriberRepository subscriberRepository;
    @Mock
    InvoiceRepository invoiceRepository;
    @InjectMocks
    NotificationService underTest;

    @Test
    void shouldNotifyAboutNewInvoice() {
        // Given
        Subscriber subscriber = mock(Subscriber.class);
        Optional<Subscriber> optionalSubscriber = Optional.of(subscriber);
        Invoice invoice = mock(Invoice.class);
        Optional<Invoice> optionalInvoice = Optional.of(invoice);

        // When
        when(subscriberRepository.getById(anyLong())).thenReturn(optionalSubscriber);
        when(invoiceRepository.get(anyLong())).thenReturn(optionalInvoice);

//        i - invoice as argument in method sendNotifyAboutNewInvoice
        doAnswer((i) -> {
            assertEquals(invoice, i.getArgument(0));
            return null;
        }).when(subscriber).sendNotifyAboutNewInvoice(invoice);
//
        // Then
        underTest.notifyAboutNewInvoice(subscriber.getId(), invoice.getId());
    }

    @Test
    void shouldReturnInvoiceIfExistOrThrowIfNot() {
        // Given
        Invoice invoice = mock(Invoice.class);
        Optional<Invoice> optionalInvoice = Optional.of(invoice);

        // When
        when(invoiceRepository.get(anyLong()))
                .thenReturn(optionalInvoice)
                .thenReturn(Optional.empty());

        // Then

        assertEquals(invoice, underTest.getInvoiceById(anyLong()));
        assertThrows(InvoiceNotExistException.class, () -> underTest.getInvoiceById(anyLong()));

    }

    @Test
    void shouldReturnSubscriberIfExistOrThrowIfNot() {
        // Given
        Subscriber subscriber = mock(Subscriber.class);
        Optional<Subscriber> optionalSubscriber = Optional.of(subscriber);

        // When
        when(subscriberRepository.getById(anyLong()))
                .thenReturn(optionalSubscriber)
                .thenReturn(Optional.empty());

        // Then

        assertEquals(subscriber, underTest.getSubscriberById(anyLong()));
        assertThrows(SubscriberNotFoundException.class, () -> underTest.getSubscriberById(anyLong()));

    }
}