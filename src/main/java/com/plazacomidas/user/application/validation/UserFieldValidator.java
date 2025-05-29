package com.plazacomidas.user.application.validation;

import com.plazacomidas.user.domain.exception.InvalidUserException;
import com.plazacomidas.user.domain.util.DateFormatterUtil;
import com.plazacomidas.user.domain.util.UserConstants;
import java.util.function.Predicate;

public enum UserFieldValidator {

    EMAIL(UserConstants.REGEX_EMAIL, UserConstants.ERROR_INVALID_EMAIL),
    DOCUMENT(UserConstants.REGEX_DOCUMENT, UserConstants.ERROR_INVALID_DOCUMENT),
    PHONE(UserConstants.REGEX_PHONE, UserConstants.ERROR_INVALID_PHONE),
    DATE(value -> {
        DateFormatterUtil.parseDate(value);
        return true;
    }, UserConstants.ERROR_INVALID_DATE_FORMAT),
    RESTAURANT_ID(
            value -> value != null && value.matches("\\d+") && Long.parseLong(value) > 0,
            UserConstants.ERROR_RESTAURANT_ID_REQUIRED
    );

    private final Predicate<String> validator;
    private final String errorMessage;

    UserFieldValidator(String regex, String errorMessage) {
        this(s -> s != null && s.matches(regex), errorMessage);
    }

    UserFieldValidator(Predicate<String> validator, String errorMessage) {
        this.validator = validator;
        this.errorMessage = errorMessage;
    }

    public void validate(String value) {
        if (!validator.test(value)) {
            throw new InvalidUserException(errorMessage);
        }
    }
}
