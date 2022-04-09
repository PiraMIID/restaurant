package pl.ki.recruitment.restaurant.domain.tables;

import pl.ki.recruitment.restaurant.domain.localization.Localization;
import pl.ki.recruitment.restaurant.domain.order.OrderChunkDTO;

import java.util.Objects;
import java.util.Set;

class Table {

    private final Localization localization;
    private final int roomId;
    private final int positionId;
    private final int placesCount;
    private Long id;
    private State state;
    private Set<OrderChunkDTO> orderChunks;

    public Table(Localization localization, int roomId, int positionId, int placesCount) {
        this.localization = localization;
        this.roomId = roomId;
        this.positionId = positionId;
        this.placesCount = placesCount;
        this.state = State.CLOSED;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public State getState() {
        return state;
    }

    public void setState(State state) {
        this.state = state;
    }

    public Localization getLocalization() {
        return localization;
    }

    public int getRoomId() {
        return roomId;
    }

    public int getPositionId() {
        return positionId;
    }

    public int getPlacesCount() {
        return placesCount;
    }

    public Set<OrderChunkDTO> getOrderChunks() {
        return orderChunks;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Table table = (Table) o;
        return roomId == table.roomId && positionId == table.positionId && placesCount == table.placesCount && Objects.equals(id, table.id) && state == table.state && Objects.equals(localization, table.localization) && Objects.equals(orderChunks, table.orderChunks);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, state, localization, roomId, positionId, placesCount, orderChunks);
    }

    public enum State {
        CLOSED, OPEN, BOOKED
    }
}
