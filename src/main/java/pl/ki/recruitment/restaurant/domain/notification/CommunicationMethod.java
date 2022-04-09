package pl.ki.recruitment.restaurant.domain.notification;

interface CommunicationMethod {

    void notify(Subscriber subscriber, Long Long, CommunicationMethodsService communicationMethodsService);
}
