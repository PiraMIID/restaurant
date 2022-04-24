package pl.ki.recruitment.restaurant.domain.tables;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Map;

@ExtendWith(MockitoExtension.class)
class OrderChunkDTOTest {

    @Spy
    OrderChunkDTO underTest;

    @BeforeEach
    void setUp() {
        underTest.items.clear();
    }

    @Test
    void shouldAddItem() {
        // Given
        // When
        underTest.addItem(1L,2);
        // Then
        Assertions.assertEquals(2, underTest.items.get(1L));

    }

    @Test
    void shouldAddCountIfAlreadyExist() {
        // Given
        // When
        underTest.addItem(1L,3);
        underTest.addItem(1L,4);
        // Then
        Assertions.assertEquals(7, underTest.items.get(1L));
    }

    @Test
    void shouldGetItems() {
        // Given
        underTest.addItem(1L, 3);
        // When
        Map<Long, Integer> items = underTest.getItems();
        // Then
        Assertions.assertEquals(underTest.items, items);
    }
}