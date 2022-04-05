package pl.ki.recruitment.restaurant.domain.invoice;

import pl.ki.recruitment.restaurant.domain.shared.kernel.ItemId;

interface ItemService {

    Item get(ItemId itemId);
}
