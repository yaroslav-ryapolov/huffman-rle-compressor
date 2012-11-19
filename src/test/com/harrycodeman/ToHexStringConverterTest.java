package test.com.harrycodeman;

import com.harrycodeman.ToHexStringConverter;
import org.junit.Test;

import static junit.framework.Assert.assertEquals;
import static org.junit.Assert.assertArrayEquals;

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
