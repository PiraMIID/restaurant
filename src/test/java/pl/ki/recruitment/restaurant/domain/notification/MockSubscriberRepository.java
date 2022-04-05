package pl.ki.recruitment.restaurant.domain.notification;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

class MockSubscriberRepository implements SubscriberRepository {

    private Map<SubscriberId, Subscriber> subscribers = new HashMap<>();

    @Override
    public Collection<Subscriber> findAll() {
        return subscribers.values().stream().collect(Collectors.toList());
    }

    Subscriber findById(SubscriberId subscriberId) {
        return subscribers.get(subscriberId);
    }

    void add(Subscriber subscriber) {
        subscribers.put(subscriber.getId(), subscriber);
    }
}
