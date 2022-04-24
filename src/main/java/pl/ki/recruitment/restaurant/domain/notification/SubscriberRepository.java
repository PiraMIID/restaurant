package pl.ki.recruitment.restaurant.domain.notification;

import java.util.Collection;
import java.util.Optional;

interface SubscriberRepository {
    Optional<Subscriber> getById(Long subscriberId);
}
