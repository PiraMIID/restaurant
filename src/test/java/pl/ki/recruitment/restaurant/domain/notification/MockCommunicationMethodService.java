package pl.ki.recruitment.restaurant.domain.notification;

import java.util.HashMap;
import java.util.Map;
import pl.ki.recruitment.restaurant.domain.shared.kernel.InvoiceId;

class MockCommunicationMethodService implements CommunicationMethodsService {

    private Map<String, InvoiceId> emailNotifications = new HashMap<>();
    private Map<String, InvoiceId> smsNotifications = new HashMap<>();
    private Map<String, InvoiceId> pigeonNotifications = new HashMap<>();
    private boolean isRecordAssertionNotification = false;
    private boolean lastNotificationFound;

    @Override
    public void notifyByEmail(String emailAddress, InvoiceId invoiceId) {
        lastNotificationFound = false;
        if (isRecordAssertionNotification) {
            if (emailNotifications.containsKey(emailAddress)) {
                if (emailNotifications.get(emailAddress).equals(invoiceId)) {
                    lastNotificationFound = true;
                }
            }
            return;
        }
        emailNotifications.put(emailAddress, invoiceId);
    }

    @Override
    public void notifyBySms(String phoneNumber, InvoiceId invoiceId) {
        lastNotificationFound = false;
        if (isRecordAssertionNotification) {
            if (smsNotifications.containsKey(phoneNumber)) {
                if (smsNotifications.get(phoneNumber).equals(invoiceId)) {
                    lastNotificationFound = true;
                }
            }
        }

        smsNotifications.put(phoneNumber, invoiceId);
    }

    @Override
    public void notifyByPigeon(String address, InvoiceId invoiceId) {
        lastNotificationFound = false;
        if (isRecordAssertionNotification) {
            if (pigeonNotifications.containsKey(address)) {
                if (pigeonNotifications.get(address).equals(invoiceId)) {
                    lastNotificationFound = true;
                }
            }
        }

        pigeonNotifications.put(address, invoiceId);
    }

    void recordAssertionNotification() {
        isRecordAssertionNotification = true;
    }

    boolean assertLastNotificationFound() {
        isRecordAssertionNotification = false;
        return lastNotificationFound;
    }
}
