package com.dmdev.util;

import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;

import static org.junit.jupiter.api.Assertions.*;

class LocalDateFormatterTest {

    @Test
    public void testValidDateFormat() {
        LocalDate expectedDate = LocalDate.of(2011, 12, 1);
        LocalDate actualDate = LocalDateFormatter.format("2011-12-01");
        assertEquals(expectedDate, actualDate);
    }

    @Test
    public void testInvalidDateFormat() {
        assertThrows(DateTimeParseException.class, () -> LocalDateFormatter.format("2011-13-01"));
    }

    @Test
    public void testInvalidDateFormatTooShort() {
        assertThrows(DateTimeParseException.class, () -> LocalDateFormatter.format("2011-12"));
    }

    @Test
    public void testInvalidDateFormatNonNumeric() {
        assertThrows(DateTimeParseException.class, () -> LocalDateFormatter.format("2011-xx-01"));
    }

    @Test
    public void testEmptyString() {
        assertThrows(DateTimeParseException.class, () -> LocalDateFormatter.format(""));
    }
}