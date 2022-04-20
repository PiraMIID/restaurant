package pl.ki.recruitment.restaurant.domain.tables;

import pl.ki.recruitment.restaurant.domain.invoice.InvoiceService;
import pl.ki.recruitment.restaurant.domain.localization.Localization;
import pl.ki.recruitment.restaurant.domain.order.OrderChunkDTO;

import static pl.ki.recruitment.restaurant.domain.tables.Table.State.*;

public class TableService {

    private final TableRepository tableRepository;
    private final InvoiceService invoiceService;

    protected TableService(TableRepository repo, InvoiceService invoiceService) {
        this.tableRepository = repo;
        this.invoiceService = invoiceService;
    }

    public Table createTable(Localization localization, int roomId, int positionId, int placesCount) {
        Table table = new Table(localization, roomId, positionId, placesCount);
        if (!tableRepository.checkIsNotAlreadyExist(localization, roomId, positionId)) {
            throw new TableAlredyExistException();
        }
        return tableRepository.create(table);
    }

    public void openTable(Long tableId) {
        Table table = findById(tableId);
        if (table.getState().equals(OPEN)) {
            throw new CannotOpenTableException();
        }
        table.setState(OPEN);
        tableRepository.save(table);

    }

    public void addOrderChunk(Long tableId, OrderChunkDTO orderChunkDTO) {
        Table table = findById(tableId);
        table.getOrderChunks().add(orderChunkDTO);
        save(table);
    }

    public void bookTable(Long tableId) {
        Table table = findById(tableId);
        if (table.getState().equals(OPEN)) {
            throw new CannotBookTableException();
        }
        table.setState(BOOKED);
        tableRepository.save(table);
    }

    public void closeTable(Long tableId) {
        Table table = findById(tableId);
        if (table.getState().equals(CLOSED)) {
            throw new CannotCloseTableException();
        }
        if (!table.getOrderChunks().isEmpty()) {
            invoiceService.createForTable(tableId);
        }
        table.setState(CLOSED);
        tableRepository.save(table);
    }

    public Table findById(Long tableId) {
        return tableRepository.findById(tableId)
                .orElseThrow(TableNotExistsException::new);
    }

    Table save(Table table) {
        return tableRepository.save(table);
    }

}
