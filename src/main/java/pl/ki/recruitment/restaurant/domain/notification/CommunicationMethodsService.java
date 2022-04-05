package pl.ki.recruitment.restaurant.domain.notification;

import pl.ki.recruitment.restaurant.domain.shared.kernel.InvoiceId;

interface CommunicationMethodsService {

    void notifyByEmail(String emailAddress, InvoiceId invoiceId);

    void notifyBySms(String phoneNumber, InvoiceId invoiceId);

    void notifyByPigeon(String address, InvoiceId invoiceId);
}
