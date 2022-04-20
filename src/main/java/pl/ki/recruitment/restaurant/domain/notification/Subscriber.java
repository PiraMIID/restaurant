package pl.ki.recruitment.restaurant.domain.notification;

class Subscriber {

    private final String email;
    private final CommunicationMethod communicationMethod;
    private final String phoneNumber;
    private Long id;
    private final String address;

    Subscriber(CommunicationMethod communicationMethod, String emailAddress, String phoneNumber, String address) {
        this.communicationMethod = communicationMethod;
        this.email = emailAddress;
        this.phoneNumber = phoneNumber;
        this.address = address;
    }

    public CommunicationMethod getPreferredCommunicationMethod() {
        return communicationMethod;
    }

    Long getId() {
        return id;
    }

    void setId(Long id) {
        this.id = id;
    }

    String getEmail() {
        return email;
    }

    String getPhoneNumber() {
        return phoneNumber;
    }

    String getAddress() {
        return address;
    }
}
