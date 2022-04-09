package pl.ki.recruitment.restaurant.domain.notification;

interface CommunicationMethodsService {

    void notifyByEmail(String emailAddress, Long Long);

    void notifyBySms(String phoneNumber, Long Long);

    void notifyByPigeon(String address, Long Long);
}
