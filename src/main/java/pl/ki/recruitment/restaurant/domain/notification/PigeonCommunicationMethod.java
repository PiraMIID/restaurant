package pl.ki.recruitment.restaurant.domain.notification;

import pl.ki.recruitment.restaurant.domain.shared.kernel.InvoiceId;

class PigeonCommunicationMethod implements CommunicanitiionMethod {

    @Override
    public void notify(Subscriber subscriber, InvoiceId invoiceId,
                       CommunicationMethodsService communicationMethodsService) {
        communicationMethodsService.notifyByPigeon(subscriber.getAddress(), invoiceId);
    }
}
