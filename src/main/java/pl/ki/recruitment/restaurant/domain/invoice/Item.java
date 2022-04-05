package pl.ki.recruitment.restaurant.domain.invoice;

class Item {

    private final Money price;
    private final Object tax;

    Item(Money price, Object tax) {
        this.price = price;
        this.tax = tax;
    }

    public Money getPrice() {
        return price;
    }

    public Object getTax() {
        return tax;
    }
}
