package pl.ki.recruitment.restaurant.domain.tables;


import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class OrderChunkDTO {

    public final Map<Long, Integer> items = new HashMap<>();

    public void addItem(Long itemId, int pieces) {
        if (items.containsKey(itemId)) {
            pieces += items.get(itemId);
        }
        items.put(itemId, pieces);
    }

    public Map<Long, Integer> getItems() {
        return items;
    }

    @Override
    public String toString() {
        return "OrderChunkDTO{" +
                "items=" + items +
                '}';
    }
}
