package pl.ki.recruitment.restaurant.domain.notification;

import pl.ki.recruitment.restaurant.domain.invoice.Invoice;

interface CommunicationMethodsService {

    void notifyByEmail(String emailAddress, Invoice invoice);

    void notifyBySms(String phoneNumber, Invoice invoice);

    void notifyByPigeon(String address, Invoice invoice);
}
