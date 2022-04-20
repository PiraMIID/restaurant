package pl.ki.recruitment.restaurant.domain.notification;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import pl.ki.recruitment.restaurant.domain.invoice.Invoice;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class NotificationServiceTest {

    @Mock
    private SubscriberRepository subscriberRepository;
    @Mock
    private CommunicationMethodsService communicationMethodsService;
    @InjectMocks
    private NotificationService underTest;

    @Test
    void shouldThrowSubscriberDoesNotExistExceptionWhenSubscriberNotExist() {

        // Given
        Optional<Subscriber> optionalSubscriber = Optional.empty();

        // When
        when(subscriberRepository.getById(anyLong())).thenReturn(optionalSubscriber);

        // Then
        assertThrows(SubscriberDoesNotExistException.class, () -> underTest.getSubscriberById(anyLong()));
    }

    @Test
    void shouldReturnedSubscriberWhenExist() {

        // Given
        Subscriber mockSubscriber = mock(Subscriber.class);
        Optional<Subscriber> optionalSubscriber = Optional.of(mockSubscriber);

        // When
        when(subscriberRepository.getById(anyLong())).thenReturn(optionalSubscriber);

        // Then
        assertDoesNotThrow(() -> underTest.getSubscriberById(anyLong()));
        assertEquals(mockSubscriber, underTest.getSubscriberById(anyLong()));
    }

    @Test
    void shouldNotifyAboutNewInvoiceByCallMethodNotifyWithPreferredCommunicationType() {

        // Given
        Subscriber mockSubscriber = mock(Subscriber.class);
        Optional<Subscriber> optionalSubscriber = Optional.of(mockSubscriber);

        Invoice mockInvoice = mock(Invoice.class);

        EmailCommunicationMethod mockEmailCommunicationMethod = mock(EmailCommunicationMethod.class);
        SmsCommunicationMethod mockSmsCommunicationMethod = mock(SmsCommunicationMethod.class);
        PigeonCommunicationMethod mockPigeonCommunicationMethod = mock(PigeonCommunicationMethod.class);

        // When
        when(subscriberRepository.getById(anyLong())).thenReturn(optionalSubscriber);

        when(mockSubscriber.getPreferredCommunicationMethod())
                .thenReturn(mockEmailCommunicationMethod)
                .thenReturn(mockSmsCommunicationMethod)
                .thenReturn(mockPigeonCommunicationMethod);

        // Then
        underTest.notifyAboutNewInvoice(anyLong(), mockInvoice);
        verify(mockEmailCommunicationMethod, times(1)).notify(mockInvoice);

        underTest.notifyAboutNewInvoice(anyLong(), mockInvoice);
        verify(mockSmsCommunicationMethod, times(1)).notify(mockInvoice);

        underTest.notifyAboutNewInvoice(anyLong(), mockInvoice);
        verify(mockPigeonCommunicationMethod, times(1)).notify(mockInvoice);


    }

}