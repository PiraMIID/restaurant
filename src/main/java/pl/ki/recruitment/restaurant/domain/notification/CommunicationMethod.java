package pl.ki.recruitment.restaurant.domain.notification;

import pl.ki.recruitment.restaurant.domain.invoice.Invoice;

interface CommunicationMethod {
    void notifyAboutNewInvoice(Subscriber subscriber, Invoice invoice);
}
