package pl.ki.recruitment.restaurant.domain.notification;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public final class SubscribersValidateUtils {

    public static final Pattern VALID_PHONE_NUMBER = Pattern.compile("^\\d{10}$");

    public static final Pattern VALID_EMAIL_ADDRESS_REGEX = Pattern.compile("^[A-Z\\d._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$", Pattern.CASE_INSENSITIVE);

    static public void validatePhoneNumber(String number) {
        Matcher matcher = VALID_PHONE_NUMBER.matcher(number);
        if (!matcher.matches()) {
            throw new IllegalArgumentException("Wrong phone number: " + number);
        }
    }

    static public void validateEmail(String emailAdders) {
        Matcher matcher = VALID_EMAIL_ADDRESS_REGEX.matcher(emailAdders);
        if (!matcher.matches()) {
            throw new IllegalArgumentException("Wrong address e-mail: " + emailAdders);
        }
    }


}
