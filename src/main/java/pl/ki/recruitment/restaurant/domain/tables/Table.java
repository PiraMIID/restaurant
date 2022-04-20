package pl.ki.recruitment.restaurant.domain.tables;

import pl.ki.recruitment.restaurant.domain.localization.Localization;
import pl.ki.recruitment.restaurant.domain.order.OrderChunkDTO;

import java.util.List;
import java.util.Objects;

public class Table {

    private Localization localization;
    private int roomId;
    private int positionId;
    private int placesCount;
    private Long id;
    private State state;
    private List<OrderChunkDTO> orderChunks;

    public Table() {
    }

    public Table(Localization localization, int roomId, int positionId, int placesCount) {
        this.localization = localization;
        this.roomId = roomId;
        this.positionId = positionId;
        this.placesCount = placesCount;
        this.state = State.CLOSED;
    }

    public Localization getLocalization() {
        return localization;
    }

    public void setLocalization(Localization localization) {
        this.localization = localization;
    }

    public int getRoomId() {
        return roomId;
    }

    public void setRoomId(int roomId) {
        this.roomId = roomId;
    }

    public int getPositionId() {
        return positionId;
    }

    public void setPositionId(int positionId) {
        this.positionId = positionId;
    }

    public int getPlacesCount() {
        return placesCount;
    }

    public void setPlacesCount(int placesCount) {
        this.placesCount = placesCount;
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

    public List<OrderChunkDTO> getOrderChunks() {
        return orderChunks;
    }

    public void setOrderChunks(List<OrderChunkDTO> orderChunks) {
        this.orderChunks = orderChunks;
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

    @Override
    public String toString() {
        return "Table{" +
                "localization=" + localization +
                ", roomId=" + roomId +
                ", positionId=" + positionId +
                ", placesCount=" + placesCount +
                ", id=" + id +
                ", state=" + state +
                ", orderChunks=" + orderChunks +
                '}';
    }
}
