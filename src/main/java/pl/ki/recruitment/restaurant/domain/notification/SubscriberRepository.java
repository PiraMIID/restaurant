package pl.ki.recruitment.restaurant.domain.notification;

import java.util.Collection;

interface SubscriberRepository {

    Collection<Subscriber> findAll();
}
