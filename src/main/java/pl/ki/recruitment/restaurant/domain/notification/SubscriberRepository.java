package pl.ki.recruitment.restaurant.domain.notification;

import java.util.Optional;

interface SubscriberRepository {
    Optional<Subscriber> getById(Long subscriberId);

    Subscriber save(Subscriber subscriber);

    boolean checkExistByEmailAndPhoneNumberAndAddress(String email, String phoneNumber, String address);
}
