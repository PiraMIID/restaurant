package pl.ki.recruitment.restaurant.domain.tables;

import java.util.Objects;

class RoomId {

    private final String id;

    public RoomId(String id) {
        this.id = id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        RoomId roomId = (RoomId) o;
        return id.equals(roomId.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
