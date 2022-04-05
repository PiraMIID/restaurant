package pl.ki.recruitment.restaurant.domain.notification;

import java.util.Objects;

class SubscriberId {

    private final String subscriberId;

    SubscriberId(String subscriberId) {
        this.subscriberId = subscriberId;
    }

    @Override
    public int hashCode() {
        return Objects.hash(subscriberId);
    }
}
