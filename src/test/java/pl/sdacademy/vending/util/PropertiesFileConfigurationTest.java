package pl.sdacademy.vending.util;

import org.junit.Test;

import static org.junit.Assert.*;

public class PropertiesFileConfigurationTest {

    @Test
    public void shouldReturnTextPropertyFromConfiguration() {
        //given
        PropertiesFileConfiguration propertiesFileConfiguration = PropertiesFileConfiguration.getInstance();
        String propertyName = "property.text.value";
        //when
        String retrievedValue = propertiesFileConfiguration.getProperty(propertyName, "DEFAULT_NAME");
        //then
        assertEquals("some text", retrievedValue);
    }

    @Test
    public void shouldReturnDefaultProperty() {
        //given
        PropertiesFileConfiguration propertiesFileConfiguration =PropertiesFileConfiguration.getInstance();
        String propertyName = "aaaaa";
        //when
        String retrievedValue = propertiesFileConfiguration.getProperty(propertyName, "DEFAULT_NAME");
        //then
        assertEquals("DEFAULT_NAME", retrievedValue);
    }

    @Test
    public void shouldReturnDefaultLongNumber() {
        //given
        PropertiesFileConfiguration propertiesFileConfiguration =PropertiesFileConfiguration.getInstance();
        String propertyName = "bbbbb";
        //when
        Long retrievedValue = propertiesFileConfiguration.getProperty(propertyName, 100L);
        //then
        assertEquals((Long) 100L, retrievedValue);
    }

    @Test
    public void shouldReturnLongNumberFromConfiguration() {
        //given
        PropertiesFileConfiguration propertiesFileConfiguration = PropertiesFileConfiguration.getInstance();
        String propertyName = "property.long.value";
        //when
        Long retrievedValue = propertiesFileConfiguration.getProperty(propertyName, 100L);
        //then
        assertEquals((Long) 1337L, retrievedValue);
    }
}