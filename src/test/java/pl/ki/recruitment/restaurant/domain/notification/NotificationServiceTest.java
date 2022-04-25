package pl.ki.recruitment.restaurant.domain.notification;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import pl.ki.recruitment.restaurant.domain.invoice.Invoice;
import pl.ki.recruitment.restaurant.domain.invoice.InvoiceNotFoundException;
import pl.ki.recruitment.restaurant.domain.invoice.InvoiceRepository;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
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
        assertThrows(InvoiceNotFoundException.class, () -> underTest.getInvoiceById(anyLong()));

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

    @Test
    void shouldThrowWhenSubscriberAlreadyExist() {
        // Given
        // When
        when(subscriberRepository.checkExistByEmailAndPhoneNumberAndAddress(anyString(), anyString(), anyString()))
                .thenReturn(true)
                .thenReturn(false);

        // Then
        Assertions.assertThrows(SubscriberAlreadyExistException.class, () -> underTest.checkSubscribeNotAlreadyExist(anyString(), anyString(), anyString()));
        Assertions.assertDoesNotThrow(() -> underTest.checkSubscribeNotAlreadyExist(anyString(), anyString(), anyString()));
    }

    @Test
    void shouldCreateNewSubscriberAndSave() {
        // Given
        String email = "dszmajduch@gmail.com";
        String phoneNumber = "1234567890";
        String address = "asgretdgbws 123";
        CommunicationMethod communicationMethod = new SmsCommunicationMethod();

        // When
        Subscriber excepted = new Subscriber(communicationMethod, email, phoneNumber, address);
        when(subscriberRepository.save(any(Subscriber.class))).thenReturn(excepted);

        Subscriber subscriber = underTest.create(communicationMethod, email, phoneNumber, address);

        // Then

        Assertions.assertEquals(excepted, subscriber);

    }
}