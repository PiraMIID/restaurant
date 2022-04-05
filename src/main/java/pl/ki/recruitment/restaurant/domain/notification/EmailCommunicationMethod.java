package pl.ki.recruitment.restaurant.domain.notification;

import pl.ki.recruitment.restaurant.domain.shared.kernel.InvoiceId;

class EmailCommunicationMethod implements CommunicanitiionMethod {

    @Override
    public void notify(Subscriber subscriber, InvoiceId invoiceId,
                       CommunicationMethodsService communicationMethodsService) {
        communicationMethodsService.notifyByEmail(subscriber.getDunno(), invoiceId);
    }
}
