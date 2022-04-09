package pl.ki.recruitment.restaurant.domain.notification;


class SmsCommunicationMethod implements CommunicationMethod {

    @Override
    public void notify(Subscriber subscriber, Long Long,
                       CommunicationMethodsService communicationMethodsService) {
        communicationMethodsService.notifyBySms(subscriber.getPhoneNumber(), Long);
    }
}
