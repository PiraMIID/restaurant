package pl.ki.recruitment.restaurant.domain.item;

import java.math.BigDecimal;

public class ItemService {

    private final ItemRepository itemRepository;

    public ItemService(ItemRepository itemRepository) {
        this.itemRepository = itemRepository;
    }

    public Item get(Long itemId) {
        return itemRepository.get(itemId)
                .orElseThrow(ItemDoNotExistException::new);
    }


    public BigDecimal calculatePrice(Long itemId, Integer count) {
        Item item = get(itemId);
        return item.getPrice().multiply(BigDecimal.valueOf(count));
    }

    public BigDecimal calculateTax(Long itemId, Integer count) {
        Item item = get(itemId);
        return item.getTax().calculateFor(item.getPrice()).multiply(BigDecimal.valueOf(count));
    }
}
