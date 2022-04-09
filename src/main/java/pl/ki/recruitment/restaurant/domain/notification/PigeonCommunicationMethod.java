package pl.ki.recruitment.restaurant.domain.notification;


class PigeonCommunicationMethod implements CommunicationMethod {

    @Override
    public void notify(Subscriber subscriber, Long Long,
                       CommunicationMethodsService communicationMethodsService) {
        communicationMethodsService.notifyByPigeon(subscriber.getAddress(), Long);
    }
}
