package test.com.harrycodeman;

import com.harrycodeman.ByteToHexStringConverter;
import org.junit.Test;

import static junit.framework.Assert.assertEquals;

public class ByteToHexStringConverterTest {
    @Test
    public void testConvertEmptyByte() {
        assertEquals("00", ByteToHexStringConverter.toHexString(0));
    }

    @Test
    public void testConvertFullByte() {
        assertEquals("FF", ByteToHexStringConverter.toHexString(255));
    }

    @Test
    public void testDifferentSymbolsByte() {
        assertEquals("3A", ByteToHexStringConverter.toHexString(58));
    }
}
