package pl.ki.recruitment.restaurant.domain.tables;

interface TableRepository {

    Table findById(TableId tableId);

            Table findBy(LocalizationId localizationId, RoomId roomId, PositionId positionId);

    TableId         save(Table table);

}
