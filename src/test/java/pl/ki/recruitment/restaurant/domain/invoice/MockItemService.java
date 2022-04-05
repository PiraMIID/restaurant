package pl.ki.recruitment.restaurant.domain.invoice;

import java.util.HashMap;
import java.util.Map;
import pl.ki.recruitment.restaurant.domain.shared.kernel.ItemId;

class MockItemService implements ItemService {

    private final Map<ItemId, Item> items = new HashMap<>();

    @Override
    public Item get(ItemId itemId) {
        return items.get(itemId);
    }

    void put(ItemId itemId, Item item) {
        items.put(itemId, item);
    }
}
