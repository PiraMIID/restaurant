package pl.ki.recruitment.restaurant.domain.localization;

import java.util.Optional;

public interface LocalizationRepositories {
    Optional<Localization> get(Long id);

}
