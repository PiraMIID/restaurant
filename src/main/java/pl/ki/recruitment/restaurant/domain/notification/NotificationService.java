package pl.ki.recruitment.restaurant.domain.notification;


import java.util.Collection;

class NotificationService {

    private final SubscriberRepository SUBSCRIBER_REPO;
    private final CommunicationMethodsService communicationMethodsService;

    NotificationService(SubscriberRepository subscriberRepo, CommunicationMethodsService communicationMethodsService) {
        this.SUBSCRIBER_REPO = subscriberRepo;
        this.communicationMethodsService = communicationMethodsService;
    }

    void notifyAboutNewInvoice(Long Long) {
        Collection<Subscriber> subscribers = findSubscribers();

        subscribers.stream().forEachOrdered(subscriber -> {
            subscriber.getPreferredCommunicationMethod().notify(subscriber, Long, communicationMethodsService);
        });
    }

    private Collection<Subscriber> findSubscribers() {
        return SUBSCRIBER_REPO.findAll();
    }
}
