package pl.ki.recruitment.restaurant.domain.item;

import java.util.Optional;

public interface ItemRepository {
    Optional<Item> get(Long itemId);

}
