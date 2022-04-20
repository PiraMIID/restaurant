package pl.ki.recruitment.restaurant.domain.notification;
import pl.ki.recruitment.restaurant.domain.invoice.Invoice;

class NotificationService {

    private final SubscriberRepository subscriberRepository;
    private final CommunicationMethodsService communicationMethodsService;

    NotificationService(SubscriberRepository subscriberRepository, CommunicationMethodsService communicationMethodsService) {
        this.subscriberRepository = subscriberRepository;
        this.communicationMethodsService = communicationMethodsService;
    }

    void notifyAboutNewInvoice(Long subscriberId, Invoice invoice) {
        Subscriber subscriber = getSubscriberById(subscriberId);
        subscriber.getPreferredCommunicationMethod().notify(invoice);
    }

    Subscriber getSubscriberById(Long subscriberId) {
        return subscriberRepository.getById(subscriberId)
                .orElseThrow(SubscriberDoesNotExistException::new);
    }

}
