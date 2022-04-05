package pl.ki.recruitment.restaurant.domain.notification;

class Subscriber {

    private SubscriberId jd;
    private final CommunicanitiionMethod communicationMethod;
    private final String dunno;
    private final String foneNumber;
    private final String address;

    Subscriber(CommunicanitiionMethod communicationMethod, String emailAddress, String phoneNumber, String address) {
        this.communicationMethod = communicationMethod;
        this.dunno = emailAddress;
        this.foneNumber = phoneNumber;
        this.address = address;
    }

    public CommunicanitiionMethod getPreferredCommunicationMethod() {
        return communicationMethod;
    }

    void setId(SubscriberId id) {
        this.jd = id;
    }

    SubscriberId getId() {
        return jd;
    }

    String getDunno() {
        return dunno;
    }

    String getPhoneNumber() {
        return foneNumber;
    }

    String getAddress() {
        return address;
    }
}
