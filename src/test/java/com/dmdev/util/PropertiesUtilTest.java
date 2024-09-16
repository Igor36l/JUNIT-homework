package com.dmdev.util;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import static org.junit.jupiter.api.Assertions.*;

public class PropertiesUtilTest {

    private static Properties properties;

    @BeforeAll
    public static void setup() throws IOException {
        properties = new Properties();
        try (InputStream inputStream = PropertiesUtilTest.class.getResourceAsStream("/application.properties")) {
            if (inputStream != null) {
                properties.load(inputStream);
            }
        }
    }

    @Test
    public void testGetExistingProperty() {
        String value = properties.getProperty("test.key");
        assertEquals("testValue", value);
    }

    @Test
    public void testGetNonExistingProperty() {
        String value = properties.getProperty("non.existing.key");
        assertNull(value);
    }

    @Test
    public void testLoadProperties() {
        String value = properties.getProperty("another.key");
        assertEquals("anotherValue", value);
    }

    @Test
    public void testGetEmptyKey() {
        String value = properties.getProperty("");
        assertNull(value);
    }

    @Test
    public void testGetNullKey() {
        assertThrows(NullPointerException.class, () -> properties.getProperty(null));
    }
}
