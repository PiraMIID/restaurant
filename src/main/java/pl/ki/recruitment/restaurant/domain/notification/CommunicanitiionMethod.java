package pl.ki.recruitment.restaurant.domain.notification;

import pl.ki.recruitment.restaurant.domain.shared.kernel.InvoiceId;

interface CommunicanitiionMethod {

    void notify(Subscriber subscriber, InvoiceId invoiceId, CommunicationMethodsService communicationMethodsService);
}
