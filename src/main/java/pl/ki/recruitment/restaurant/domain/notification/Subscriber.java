package pl.ki.recruitment.restaurant.domain.notification;

import pl.ki.recruitment.restaurant.domain.invoice.Invoice;

import java.util.Objects;

class Subscriber {
    private Long id;
    private final String email;
    private final String phoneNumber;
    private final String address;
    private final CommunicationMethod communicationMethod;

    Subscriber(CommunicationMethod communicationMethod, String emailAddress, String phoneNumber, String address) {
        this.communicationMethod = communicationMethod;
        this.email = emailAddress;
        this.phoneNumber = phoneNumber;
        this.address = address;
    }

    public void sendNotifyAboutNewInvoice(Invoice invoice) {
        this.getCommunicationMethod().notifyAboutNewInvoice(this, invoice);
    }

    public String getEmail() {
        return email;
    }

    public CommunicationMethod getCommunicationMethod() {
        return communicationMethod;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getAddress() {
        return address;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Subscriber that = (Subscriber) o;
        return Objects.equals(email, that.email) && Objects.equals(communicationMethod, that.communicationMethod) && Objects.equals(phoneNumber, that.phoneNumber) && Objects.equals(id, that.id) && Objects.equals(address, that.address);
    }

    @Override
    public int hashCode() {
        return Objects.hash(email, communicationMethod, phoneNumber, id, address);
    }
}
