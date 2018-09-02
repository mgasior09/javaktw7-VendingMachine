package pl.sdacademy.vending.util;

import org.junit.Test;

import static org.junit.Assert.*;

public class ConfigurationTest {

    @Test
    public void shouldReturnTextPropertyFromConfiguration() {
        //given
        Configuration configuration = new Configuration();
        String propertyName = "property.text.value";
        //when
        String retrievedValue = configuration.getProperty(propertyName, "DEFAULT_NAME");
        //then
        assertEquals("some text", retrievedValue);

    }

    @Test
    public void shouldReturnDefaultProperty() {
        //given
        Configuration configuration = new Configuration();
        String propertyName = "aaaaa";
        //when
        String retrievedValue = configuration.getProperty(propertyName, "DEFAULT_NAME");
        //then
        assertEquals("DEFAULT_NAME", retrievedValue);
    }

    @Test
    public void shouldReturnDefaultLongNumber() {
        //given
        Configuration configuration = new Configuration();
        String propertyName = "bbbbb";
        //when
        Long retrievedValue = configuration.getProperty(propertyName, 100L);
        //then
        assertEquals(100L, retrievedValue, 0);
    }

    @Test
    public void shouldReturnLongNumberFromConfiguration() {
        //given
        Configuration configuration = new Configuration();
        String propertyName = "property.long.value";
        //when
        Long retrievedValue = configuration.getProperty(propertyName, 100L);
        //then
        assertEquals(1337, retrievedValue, 0);
    }


}