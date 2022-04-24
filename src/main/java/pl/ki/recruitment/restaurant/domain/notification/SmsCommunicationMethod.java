package pl.ki.recruitment.restaurant.domain.notification;


import pl.ki.recruitment.restaurant.domain.invoice.Invoice;

class SmsCommunicationMethod implements CommunicationMethod {

    @Override
    public void notifyAboutNewInvoice(Subscriber subscriber, Invoice invoice) {
//        todo: to implement
    }
}
