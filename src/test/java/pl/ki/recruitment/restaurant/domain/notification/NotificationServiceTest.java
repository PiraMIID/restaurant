package pl.ki.recruitment.restaurant.domain.notification;

import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;
import pl.ki.recruitment.restaurant.domain.shared.kernel.InvoiceId;

class NotificationServiceTest {

    private MockSubscriberRepository repo = new MockSubscriberRepository();
    private MockCommunicationMethodService communicationMethodsService = new MockCommunicationMethodService();
    private NotificationService service = new NotificationService(repo, communicationMethodsService);

    @Test
    public void test1() {
        //given
        subscriber("1").withEmailAddress("e1@test.com").withCommunicationMethod(emailCommunicationMethod()).create();
        subscriber("2").withPhoneNumber("11-221-221").withCommunicationMethod(smsCommunicationMethod()).create();
        subscriber("3").withAddress("10 Downing Str. London").withCommunicationMethod(pigeonCommunicationMethod())
                       .create();

        //when
        notifyAboutNewInvoice("12321");

        //then
        assertSubscriberNotified("1", "12321");
        assertSubscriberNotified("2", "12321");
        assertSubscriberNotified("3", "12321");
    }

    private void notifyAboutNewInvoice(String invoiceId) {
        service.notifyAboutNewInvoice(new InvoiceId(invoiceId));
    }

    private CommunicanitiionMethod emailCommunicationMethod() {
        return new EmailCommunicationMethod();
    }

    private CommunicanitiionMethod smsCommunicationMethod() {
        return new SmsCommunicationMethod();
    }

    private CommunicanitiionMethod pigeonCommunicationMethod() {
        return new PigeonCommunicationMethod();
    }

    private SubscriberAssembler subscriber(String subscriberId) {
        return new SubscriberAssembler(new SubscriberId(subscriberId));
    }

    private void assertSubscriberNotified(String subscriberId, String invoiceId) {
        Subscriber subscriber = repo.findById(new SubscriberId(subscriberId));
        CommunicanitiionMethod commMethod = subscriber.getPreferredCommunicationMethod();

        communicationMethodsService.recordAssertionNotification();
        commMethod.notify(subscriber, new InvoiceId(invoiceId), communicationMethodsService);
        assertTrue(communicationMethodsService.assertLastNotificationFound());
    }

    private class SubscriberAssembler {

        private final SubscriberId subscriberId;
        private CommunicanitiionMethod communicationMethod;
        private String emailAddress;
        private String phoneNumber;
        private String address;

        public SubscriberAssembler(SubscriberId subscriberId) {
            this.subscriberId = subscriberId;
        }

        public SubscriberAssembler withEmailAddress(String emailAddress) {
            this.emailAddress = emailAddress;

            return this;
        }

        public SubscriberAssembler withAddress(String address) {
            this.address = address;

            return this;
        }

        public SubscriberAssembler withPhoneNumber(String phoneNumber) {
            this.phoneNumber = phoneNumber;

            return this;
        }

        public SubscriberAssembler withCommunicationMethod(CommunicanitiionMethod communicationMethod) {
            this.communicationMethod = communicationMethod;

            return this;
        }

        public void create() {
            Subscriber subscriber = new Subscriber(communicationMethod, emailAddress, phoneNumber, address);
            subscriber.setId(subscriberId);

            repo.add(subscriber);
        }
    }
}