package pl.ki.recruitment.restaurant.domain.localization;

public class LocalizationService {

    private final LocalizationRepositories localizationRepositories;

    public LocalizationService(LocalizationRepositories localizationRepositories) {
        this.localizationRepositories = localizationRepositories;
    }

    Localization getById(Long idLocalization) {
        return localizationRepositories.get(idLocalization)
                .orElseThrow(LocalizationDoNotExistException::new);
    }
}
