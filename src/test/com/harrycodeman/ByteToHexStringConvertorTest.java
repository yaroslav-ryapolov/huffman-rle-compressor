package test.com.harrycodeman;

import com.harrycodeman.ByteToHexStringConvertor;
import org.junit.Test;

import static junit.framework.Assert.assertEquals;

public class ByteToHexStringConvertorTest {
    @Test
    public void testConvertEmptyByte() {
        assertEquals("x00", ByteToHexStringConvertor.Convert(0));
    }

    @Test
    public void testConvertFullByte() {
        assertEquals("xFF", ByteToHexStringConvertor.Convert(255));
    }

    @Test
    public void testDifferentSymbolsByte() {
        assertEquals("x3A", ByteToHexStringConvertor.Convert(58));
    }
}
