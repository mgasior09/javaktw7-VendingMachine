package pl.sdacademy.vending.util;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.util.Arrays;
import java.util.Collection;

import static org.junit.Assert.assertEquals;

@RunWith(Parameterized.class)
public class StringUtilsTest {
    private Long val1;
    private String val2;

    public StringUtilsTest(Long val1, String val2) {
        this.val1 = val1;
        this.val2 = val2;
    }

    @Parameterized.Parameters
    public static Collection<Object[]> getParameters() {
        Object[][] params = new Object[][]{
                {2L, "0,02"},
                {123L, "1,23"},
                {123456L, "1 234,56"},
                {12345L, "123,45"},
                {3L, "0,03"},
                {0L, "0,00"},
                {1234567891234567890L, "12 345 678 912 345 678,90"},
                {12L, "0,12"},

        };
        return Arrays.asList(params);
    }

    @Test
    public void shouldInsertCommaAndSpaces() {
        //when
        String adjustedText = StringUtils.formatMoney(val1);

        //then
        assertEquals(val2, adjustedText);
    }

    @Test
    public void shouldNotModifyMatchingText() {
        //given
        String textToAdjust = "Pawel";
        Integer expectedTextLength = 5;

        //when
        String adjustedText = StringUtils.adjustText(textToAdjust, expectedTextLength);

        //then
        assertEquals("Pawel", adjustedText);
    }

    @Test
    public void shouldTrimTooLongText() {
        //given
        String textToAdjust = "Ala ma kota";
        Integer expectedTextLength = 8;

        //when
        String adjustedText = StringUtils.adjustText(textToAdjust, expectedTextLength);

        //then
        assertEquals("Ala ma k", adjustedText);
    }

    @Test
    public void shouldCenterTextWhenSpacesAreEqual() {
        //given
        String textToAdjust = "Ala";
        Integer expectedTextLength = 7;

        //when
        String adjustedText = StringUtils.adjustText(textToAdjust, expectedTextLength);

        //then
        assertEquals("  Ala  ", adjustedText);
    }

    @Test
    public void shouldCenterTextWhenSpacesAreNotEqual() {
        //given
        String textToAdjust = "Ala";
        Integer expectedTextLength = 8;

        //when
        String adjustedText = StringUtils.adjustText(textToAdjust, expectedTextLength);

        //then
        assertEquals("   Ala  ", adjustedText);
    }




}