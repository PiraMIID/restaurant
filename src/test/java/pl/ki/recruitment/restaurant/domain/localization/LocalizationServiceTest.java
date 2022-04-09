package pl.ki.recruitment.restaurant.domain.localization;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;

@ExtendWith(MockitoExtension.class)
class LocalizationServiceTest {

    @Mock
    Localization localization;

    @Mock
    LocalizationRepositories localizationRepositories;

    @InjectMocks
    LocalizationService underTest;


    @Test
    void shouldReturnLocalization() {
        // When
        Long id = 1L;
        // Then
        Localization localization = new Localization(id, "asd", "city", "asdwqasdfg");

        given(localizationRepositories.get(any(Long.class))).willReturn(Optional.of(localization));
//        given(underTest.getById(any(Long.class))).willReturn(Optional.empty()).
        Localization testLocalization = underTest.getById(id);
        // Given
        assertEquals(localization, testLocalization);
    }

    @Test
    void shouldThrowWhenLocalizationDoNotExist() {
        // When
        Long id = 1L;
        // Then
        given(localizationRepositories.get(any(Long.class))).willReturn(Optional.empty());
//        given(underTest.getById(any(Long.class))).willReturn(Optional.empty()).
        // Given
        assertThrows(LocalizationDoNotExistException.class, () -> underTest.getById(id));
    }
}