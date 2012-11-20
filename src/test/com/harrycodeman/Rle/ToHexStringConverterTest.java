package test.com.harrycodeman.rle;

import com.harrycodeman.rle.ToHexStringConverter;
import org.junit.Test;

import static junit.framework.Assert.assertEquals;

public class ToHexStringConverterTest {
    @Test
    public void testConvertEmptyByte() {
        assertEquals("x00", ToHexStringConverter.convertByte(0));
    }

    @Test
    public void testConvertFullByte() {
        assertEquals("xFF", ToHexStringConverter.convertByte(255));
    }

    @Test
    public void testDifferentSymbolsByte() {
        assertEquals("x3A", ToHexStringConverter.convertByte(58));
    }
}
