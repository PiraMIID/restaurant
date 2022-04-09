package pl.ki.recruitment.restaurant.domain.notification;


import java.util.HashMap;
import java.util.Map;

class MockCommunicationMethodService implements CommunicationMethodsService {

    private final Map<String, Long> emailNotifications = new HashMap<>();
    private final Map<String, Long> smsNotifications = new HashMap<>();
    private final Map<String, Long> pigeonNotifications = new HashMap<>();
    private boolean isRecordAssertionNotification = false;
    private boolean lastNotificationFound;

    @Override
    public void notifyByEmail(String emailAddress, Long Long) {
        lastNotificationFound = false;
        if (isRecordAssertionNotification) {
            if (emailNotifications.containsKey(emailAddress)) {
                if (emailNotifications.get(emailAddress).equals(Long)) {
                    lastNotificationFound = true;
                }
            }
            return;
        }
        emailNotifications.put(emailAddress, Long);
    }

    @Override
    public void notifyBySms(String phoneNumber, Long Long) {
        lastNotificationFound = false;
        if (isRecordAssertionNotification) {
            if (smsNotifications.containsKey(phoneNumber)) {
                if (smsNotifications.get(phoneNumber).equals(Long)) {
                    lastNotificationFound = true;
                }
            }
        }

        smsNotifications.put(phoneNumber, Long);
    }

    @Override
    public void notifyByPigeon(String address, Long Long) {
        lastNotificationFound = false;
        if (isRecordAssertionNotification) {
            if (pigeonNotifications.containsKey(address)) {
                if (pigeonNotifications.get(address).equals(Long)) {
                    lastNotificationFound = true;
                }
            }
        }

        pigeonNotifications.put(address, Long);
    }

    void recordAssertionNotification() {
        isRecordAssertionNotification = true;
    }

    boolean assertLastNotificationFound() {
        isRecordAssertionNotification = false;
        return lastNotificationFound;
    }
}
