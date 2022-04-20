package pl.ki.recruitment.restaurant.domain.item;

public class ItemService {

    private final ItemRepository itemRepository;

    public ItemService(ItemRepository itemRepository) {
        this.itemRepository = itemRepository;
    }

    public Item get(Long itemId) {
        return itemRepository.get(itemId)
                .orElseThrow(ItemWithThisIdNotExistException::new);
    }

}
