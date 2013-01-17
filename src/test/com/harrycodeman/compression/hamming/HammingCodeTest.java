package test.com.harrycodeman.compression.hamming;

import com.harrycodeman.compression.hamming.HammingCode;
import org.junit.Test;

import static junit.framework.Assert.assertEquals;

public class HammingCodeTest {
    @Test
    public void testSimpleEncode() throws Exception {
        int toEncode = 0b10101101011;
        int expected = 0b111001011101011;
        assertEquals(expected, HammingCode.encode(toEncode));
    }
}
