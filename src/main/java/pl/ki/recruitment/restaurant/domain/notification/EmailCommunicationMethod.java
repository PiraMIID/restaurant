package pl.ki.recruitment.restaurant.domain.notification;

class EmailCommunicationMethod implements CommunicationMethod {

    @Override
    public void notify(Subscriber subscriber, Long Long,
                       CommunicationMethodsService communicationMethodsService) {
        communicationMethodsService.notifyByEmail(subscriber.getDunno(), Long);
    }
}
