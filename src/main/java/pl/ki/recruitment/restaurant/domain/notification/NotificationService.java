package pl.ki.recruitment.restaurant.domain.notification;

import java.util.Collection;
import pl.ki.recruitment.restaurant.domain.shared.kernel.InvoiceId;

class NotificationService {

    private final SubscriberRepository SUBSCRIBER_REPO;
    private final CommunicationMethodsService communicationMethodsService;

    NotificationService(SubscriberRepository subscriberRepo, CommunicationMethodsService communicationMethodsService) {
        this.SUBSCRIBER_REPO = subscriberRepo;
        this.communicationMethodsService = communicationMethodsService;
    }

    void notifyAboutNewInvoice(InvoiceId invoiceId) {
        Collection<Subscriber> subscribers = findSubscribers();

        subscribers.stream().forEachOrdered(subscriber -> {
            subscriber.getPreferredCommunicationMethod().notify(subscriber, invoiceId, communicationMethodsService);
        });
    }

    private Collection<Subscriber> findSubscribers() {
        return SUBSCRIBER_REPO.findAll();
    }
}
