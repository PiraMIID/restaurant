package pl.ki.recruitment.restaurant.domain.notification;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class SubscribersValidateUtilsTest {

    @Test
    void shouldValidatePhoneNumber() {
        // Given
        String tooShort = "52135123";
        String tooLong = "52135123412345";
        String withSpace = "0 312 213 123";
        String withChar = "0a31212321";
        String good = "1231231233";
        // When
        // Then
        Assertions.assertThrows(IllegalArgumentException.class, () -> SubscribersValidateUtils.validatePhoneNumber(tooShort));
        Assertions.assertThrows(IllegalArgumentException.class, () -> SubscribersValidateUtils.validatePhoneNumber(tooLong));
        Assertions.assertThrows(IllegalArgumentException.class, () -> SubscribersValidateUtils.validatePhoneNumber(withSpace));
        Assertions.assertThrows(IllegalArgumentException.class, () -> SubscribersValidateUtils.validatePhoneNumber(withChar));
        Assertions.assertDoesNotThrow(() -> SubscribersValidateUtils.validatePhoneNumber(good));
    }

    @Test
    void shouldValidateEmailAddress() {
        // Given
        String withoutMonkey = "asfer.com";
        String withoutDot = "qasf@asd";
        String withSpace = "asd asd@asd.pl";
        String withNumberAfterDot = "vbas32@ot.p3";
        String goodWithNumberAfterMonkey = "vbas32@o2.pl";
        String good = "dszmajduch@gmail.com";
        // When
        // Then
        Assertions.assertThrows(IllegalArgumentException.class, () -> SubscribersValidateUtils.validateEmail(withoutMonkey));
        Assertions.assertThrows(IllegalArgumentException.class, () -> SubscribersValidateUtils.validateEmail(withoutDot));
        Assertions.assertThrows(IllegalArgumentException.class, () -> SubscribersValidateUtils.validateEmail(withSpace));
        Assertions.assertThrows(IllegalArgumentException.class, () -> SubscribersValidateUtils.validateEmail(withNumberAfterDot));
        Assertions.assertDoesNotThrow(() -> SubscribersValidateUtils.validateEmail(goodWithNumberAfterMonkey));
        Assertions.assertDoesNotThrow(() -> SubscribersValidateUtils.validateEmail(good));
    }

}