package pl.ki.recruitment.restaurant.domain.tables;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

class MockTableRepository implements TableRepository {

    private Map<ComplexKey, Table> tables = new HashMap<>();

    @Override
    public Table findById(TableId tableId) {
        throw new UnsupportedOperationException("Not implemented yet");
        //return null;
    }

    @Override
    public Table findBy(LocalizationId localizationId, RoomId roomId, PositionId positionId) {
        ComplexKey key = new ComplexKey(localizationId, roomId, positionId);

        return tables.get(key);
    }

    @Override
    public TableId save(Table table) {
        ComplexKey key = new ComplexKey(table.getLocalizationId(), table.getRoomId(), table.getPositionId());

        if (!tables.containsKey(key)) {
            table.setId(new TableId(tables.size() + ""));
        }

        tables.put(key, table);

        return table.getId();
    }

    int getTablesCount() {
        return tables.size();
    }

    private static class ComplexKey {

        private LocalizationId localizationId;
        private RoomId roomId;
        private PositionId positionId;

        public ComplexKey(LocalizationId localizationId, RoomId roomId, PositionId positionId) {
            this.localizationId = localizationId;
            this.roomId = roomId;
            this.positionId = positionId;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) {
                return true;
            }
            if (o == null || getClass() != o.getClass()) {
                return false;
            }
            ComplexKey tableId = (ComplexKey) o;
            return localizationId.equals(tableId.localizationId) && roomId.equals(tableId.roomId) && positionId.equals(
                    tableId.positionId);
        }

        @Override
        public int hashCode() {
            return Objects.hash(localizationId, roomId, positionId);
        }
    }
}
