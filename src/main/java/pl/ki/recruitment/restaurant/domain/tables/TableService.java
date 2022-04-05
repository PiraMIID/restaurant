package pl.ki.recruitment.restaurant.domain.tables;

import pl.ki.recruitment.restaurant.domain.shared.kernel.OrderChunkDTO;

class TableService {

    private final TableRepository repo;
    private final InvoiceService invoiceService;

    protected TableService(TableRepository repo, InvoiceService invoiceService) {
        this.repo = repo;
        this.invoiceService = invoiceService;
    }

    public TableId createTable(LocalizationId localizationId, String roomId, String positionId,
                               PlacesCount placesCount) {
        vetoIfTableAlreadyExists(localizationId, roomId, positionId);

        Table table = new Table(localizationId, roomId, positionId, placesCount);

        return repo.save(table);
    }

    public void openTable(TableId tableId) {
        Table table = findBy(tableId);

        table.open();

        save(table);
    }

    public void addOrderChunk(TableId tableId, OrderChunkDTO orderChunkDTO) {
        Table table = findBy(tableId);

        table.addOrderChunk(orderChunkDTO);

        save(table);
    }

    public void bookTable(TableId tableId) {
        Table table = repo.findById(tableId);
        vetoIfTableNotExists(table, tableId);

        table.book();

        save(table);
    }

    public void closeTable(TableId tableId) {
        Table table = findBy(tableId);

        table.close(invoiceService);

        save(table);
    }

    private Table findBy(TableId tableId) {
        Table table = repo.findById(tableId);
        vetoIfTableNotExists(table, tableId);

        return table;
    }

    private void vetoIfTableNotExists(Table table, TableId tableId) {
        if (table == null) {
            throw new TableNotExistsException(tableId);
        }
    }

    private void save(Table table) {
        repo.save(table);
    }

    private void vetoIfTableAlreadyExists(LocalizationId localizationId, String roomId, String positionId) {
        if (repo.findBy(localizationId, new RoomId(roomId), new PositionId(positionId)) != null) {
            throw new CannotCreateTableException();
        }
    }
}
