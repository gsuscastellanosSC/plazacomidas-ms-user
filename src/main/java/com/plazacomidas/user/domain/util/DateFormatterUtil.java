package com.plazacomidas.user.domain.util;

import com.plazacomidas.user.domain.exception.InvalidUserException;


import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.List;
import java.util.Objects;

public class DateFormatterUtil {

    public static LocalDate parseDate(String date) {
        return getValidFormats().stream()
                .map(format -> tryParseDate(date, format))
                .filter(Objects::nonNull)
                .findFirst()
                .orElseThrow(() -> new InvalidUserException(UserConstants.ERROR_INVALID_DATE_FORMAT));
    }

    private static List<String> getValidFormats() {
        return List.of(
                UserConstants.DATE_FORMAT_DD_MM_YYYY_SLASH,
                UserConstants.DATE_FORMAT_DD_MM_YYYY_DASH
        );
    }

    private static LocalDate tryParseDate(String date, String format) {
        try {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern(format);
            return LocalDate.parse(date, formatter);
        } catch (DateTimeParseException ex) {
            return null;
        }
    }
}
