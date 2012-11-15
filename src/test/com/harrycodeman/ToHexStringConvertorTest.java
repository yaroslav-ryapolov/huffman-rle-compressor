package test.com.harrycodeman;

import com.harrycodeman.ToHexStringConvertor;
import org.junit.Test;

import static junit.framework.Assert.assertEquals;

public class ToHexStringConvertorTest {
    @Test
    public void testConvertEmptyByte() {
        assertEquals("x00", ToHexStringConvertor.ConvertByte(0));
    }

    @Test
    public void testConvertFullByte() {
        assertEquals("xFF", ToHexStringConvertor.ConvertByte(255));
    }

    @Test
    public void testDifferentSymbolsByte() {
        assertEquals("x3A", ToHexStringConvertor.ConvertByte(58));
    }

    @Test
    public void testConvertEmptyInteger() {
        assertEquals("x00", ToHexStringConvertor.ConvertCounter(0));
    }

    @Test
    public void testConvertFullInteger() {
        assertEquals("??", ToHexStringConvertor.ConvertCounter(Integer.MAX_VALUE));
    }

    @Test
    public void testConvertDifferentSymbolsInteger() {
        assertEquals("??", ToHexStringConvertor.ConvertCounter(7777777));
    }
}
