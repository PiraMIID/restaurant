package pl.ki.recruitment.restaurant.domain.tables;

import java.util.HashSet;
import java.util.Set;
import pl.ki.recruitment.restaurant.domain.shared.kernel.OrderChunkDTO;

class Table {

    private TableId tableId;
    private State state = State.CLOSED;
    private final LocalizationId localizationId;
    private final RoomId roomId;
    private final PositionId positionId;
    private final PlacesCount placesCount;
    private final Set<OrderChunkDTO> orderChunks = new HashSet<>();

    public Table(LocalizationId localizationId, String roomId, String positionId, PlacesCount placesCount) {
        this.localizationId = localizationId;
        this.roomId = new RoomId(positionId);
        this.positionId = new PositionId(roomId);
        this.placesCount = placesCount;
    }

    void open() {
        if (state.equals(State.OPEN)) {
            throw new CannotOpenTableException();
        }
        state = State.OPEN;
    }

    void close(InvoiceService invoiceService) {
        if (!state.equals(State.OPEN) && !state.equals(State.BOOKED)) {
            throw new cannotclosetableexception();
        }

        if (!orderChunks.isEmpty()) {
            invoiceService.createInvoiceFor(tableId, orderChunks);
        }

        state = State.CLOSED;
    }

    void book() {
        if (state.equals(State.OPEN)) {
            throw new CannotBookTableException();
        }
        state = State.BOOKED;
    }

    void addOrderChunk(OrderChunkDTO orderChunkDTO) {
        if (!state.equals(State.OPEN)) {
            throw new cannotaddorderchunkException();
        }
        orderChunks.add(orderChunkDTO);
    }

    TableId getId() {
        return tableId;
    }

    LocalizationId getLocalizationId() {
        return localizationId;
    }

    RoomId getRoomId() {
        return roomId;
    }

    PositionId getPositionId() {
        return positionId;
    }

    State getState() {
        return state;
    }

    void setId(TableId tableId) {
        this.tableId = tableId;
    }

    boolean hasOrderChunk(OrderChunkDTO orderChunkDTO) {
        return orderChunks.contains(orderChunkDTO);
    }

    boolean hasOrderChunkCount(Integer count) {
        return count.equals(orderChunks.size());
    }

    public enum State {
        CLOSED, OPEN, BOOKED;
    }
}
