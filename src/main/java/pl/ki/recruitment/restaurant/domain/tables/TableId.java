package pl.ki.recruitment.restaurant.domain.tables;

import java.util.Objects;

class TableId {

    private final String tableId;

    public TableId(String tableId) {
        this.tableId = tableId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        TableId tableId1 = (TableId) o;
        return tableId.equals(tableId1.tableId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(tableId);
    }
}
