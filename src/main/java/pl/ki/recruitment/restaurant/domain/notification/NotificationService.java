package pl.ki.recruitment.restaurant.domain.notification;
import pl.ki.recruitment.restaurant.domain.invoice.Invoice;
import pl.ki.recruitment.restaurant.domain.invoice.InvoiceNotExistException;
import pl.ki.recruitment.restaurant.domain.invoice.InvoiceRepository;

class NotificationService {

    private final SubscriberRepository subscriberRepository;
    private final InvoiceRepository invoiceRepository;

    NotificationService(SubscriberRepository subscriberRepository, InvoiceRepository invoiceRepository) {
        this.subscriberRepository = subscriberRepository;
        this.invoiceRepository = invoiceRepository;
    }

    void notifyAboutNewInvoice(Long subscriberId, Long invoiceId) {
        Subscriber subscriber = getSubscriberById(subscriberId);
        Invoice invoice = getInvoiceById(invoiceId);
        subscriber.sendNotifyAboutNewInvoice(invoice);
    }

    Invoice getInvoiceById(Long invoiceId) {
        return invoiceRepository.get(invoiceId)
                .orElseThrow(InvoiceNotExistException::new);
    }

    Subscriber getSubscriberById(Long subscriberId) {
        return subscriberRepository.getById(subscriberId)
                .orElseThrow(SubscriberNotFoundException::new);
    }

}
