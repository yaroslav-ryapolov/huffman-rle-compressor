package test.com.harrycodeman;

import com.harrycodeman.ToHexStringConverter;
import org.junit.Test;

import static junit.framework.Assert.assertEquals;

public class ToHexStringConverterTest {
    @Test
    public void testConvertEmptyByte() {
        assertEquals("x00", ToHexStringConverter.ConvertByte(0));
    }

    @Test
    public void testConvertFullByte() {
        assertEquals("xFF", ToHexStringConverter.ConvertByte(255));
    }

    @Test
    public void testDifferentSymbolsByte() {
        assertEquals("x3A", ToHexStringConverter.ConvertByte(58));
    }

    @Test
    public void testConvertEmptyInteger() {
        assertEquals("x00", ToHexStringConverter.ConvertInteger(0));
    }

    @Test
    public void testConvertFullInteger() {
        assertEquals("x7F xFF xFF xFF", ToHexStringConverter.ConvertInteger(Integer.MAX_VALUE));
    }

    @Test
    public void testConvertDifferentSymbolsInteger() {
        assertEquals("x76 xAD xF1", ToHexStringConverter.ConvertInteger(7777777));
    }
}
