package pl.ki.recruitment.restaurant.domain.item;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.api.function.Executable;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import pl.ki.recruitment.restaurant.domain.item.tax.Tax10Percent;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class ItemServiceTest {

    @Mock
    ItemRepository itemRepository;
    @InjectMocks
    ItemService underTest;

    @Test
    void shouldGetItemOtThrowWhenDoNotExist() {
        // Given
        Item mockTable = mock(Item.class);
        given(itemRepository.get(anyLong()))
                .willReturn(Optional.of(mockTable))
                .willReturn(Optional.empty());

        // When
        Executable test = () -> underTest.get(anyLong());

        // Then
        Assertions.assertDoesNotThrow(test);
        Assertions.assertThrows(ItemDoNotExistException.class, test);

    }

    @Test
    void shouldCalculatePriceToCount() {
        // Given
        BigDecimal price = BigDecimal.valueOf(12L);
        Integer count = 11;
        Item item = new Item(price, new Tax10Percent());
        // When
        when(itemRepository.get(anyLong())).thenReturn(Optional.of(item));
        // Then
        BigDecimal bigDecimal = underTest.calculatePrice(anyLong(), count);

        BigDecimal expected = BigDecimal.valueOf(132L).setScale(2, RoundingMode.HALF_EVEN);
        Assertions.assertEquals(expected, bigDecimal);
    }


    @Test
    void shouldCalculateTaxToCount() {
        // Given
        BigDecimal price = BigDecimal.valueOf(12L);
        Integer count = 11;
        Item item = new Item(price, new Tax10Percent());
        // When
        when(itemRepository.get(anyLong())).thenReturn(Optional.of(item));
        // Then
        BigDecimal bigDecimal = underTest.calculateTax(anyLong(), count);

        BigDecimal expected = new BigDecimal("13.20").setScale(2, RoundingMode.HALF_EVEN);
        assertEquals(expected, bigDecimal);
    }

}