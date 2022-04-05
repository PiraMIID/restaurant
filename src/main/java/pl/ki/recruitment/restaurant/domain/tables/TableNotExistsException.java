package pl.ki.recruitment.restaurant.domain.tables;

class TableNotExistsException extends RuntimeException {

    private final TableId tableId;

    public TableNotExistsException(TableId tableId) {
        this.tableId = tableId;
    }
}
