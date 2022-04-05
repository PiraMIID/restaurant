package pl.ki.recruitment.restaurant.domain.tables;

import java.util.Objects;

class PositionId {

    private final String id;

    public PositionId(String id) {
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
        PositionId that = (PositionId) o;
        return id.equals(that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
