package pl.ki.recruitment.restaurant.domain.order;


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
