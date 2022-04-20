package pl.ki.recruitment.restaurant.domain.tables;

import pl.ki.recruitment.restaurant.domain.localization.Localization;

import java.util.Optional;

public interface TableRepository {

    Optional<Table> findById(Long tableId);

    Optional<Table> findBy(Localization localizationId, int roomId, int positionId);

    boolean checkIsNotAlreadyExist(Localization localizationId, int roomId, int positionId);

    Table create(Table table);

    Table save(Table table);

}
