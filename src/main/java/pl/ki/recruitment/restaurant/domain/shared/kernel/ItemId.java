package pl.ki.recruitment.restaurant.domain.shared.kernel;

import java.util.Objects;

public class ItemId {

    private final String itemId;

    public ItemId(String itemId) {
        this.itemId = itemId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        ItemId itemId1 = (ItemId) o;
        return itemId.equals(itemId1.itemId)
    }

    @Override
    public int hashCode() {
        return Objects.hash(itemId);
    }
}
