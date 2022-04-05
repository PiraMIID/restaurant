package pl.ki.recruitment.restaurant.domain.shared.kernel;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.Set;

public class OrderChunkDTO {

    private final Map<ItemId, Integer> items = new HashMap<>();

    public void addItem(ItemId itemId, int pieces) {
        if (items.containsKey(itemId)) {
            pieces += items.get(itemId);
        }

        items.put(itemId, pieces);
    }

    public Set<ItemId> getItems() {
        return items.keySet();
    }

    public Integer getCount(ItemId itemId) {
        return items.get(itemId);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        OrderChunkDTO that = (OrderChunkDTO) o;
        return items.equals(that.items);
    }

    @Override
    public int hashCode() {
        return Objects.hash(items);
    }
}
