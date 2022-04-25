package pl.ki.recruitment.restaurant.domain.notification;
import pl.ki.recruitment.restaurant.domain.invoice.Invoice;
import pl.ki.recruitment.restaurant.domain.invoice.InvoiceNotFoundException;
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
                .orElseThrow(InvoiceNotFoundException::new);
    }

    Subscriber getSubscriberById(Long subscriberId) {
        return subscriberRepository.getById(subscriberId)
                .orElseThrow(SubscriberNotFoundException::new);
    }

    Subscriber create(CommunicationMethod communicationMethod, String email, String phoneNumber, String address) {
        SubscribersValidateUtils.validateEmail(email);
        SubscribersValidateUtils.validatePhoneNumber(phoneNumber);
        checkSubscribeNotAlreadyExist(email, phoneNumber, address);
        Subscriber subscriber = new Subscriber(communicationMethod, email, phoneNumber, address);
        return subscriberRepository.save(subscriber);
    }

    public void checkSubscribeNotAlreadyExist(String email, String phoneNumber, String address) {
        if (subscriberRepository.checkExistByEmailAndPhoneNumberAndAddress(email, phoneNumber, address)) {
            throw new SubscriberAlreadyExistException();
        }

    }
}
