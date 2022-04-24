package pl.ki.recruitment.restaurant.domain.tables;

import pl.ki.recruitment.restaurant.domain.localization.Localization;

import java.util.Optional;

public interface TableRepository {

    Optional<Table> findById(Long tableId);

    boolean checkIsNotAlreadyExist(Long localizationId,Long roomId,Long positionId);

    Table create(Table table);

    Table save(Table table);

}
