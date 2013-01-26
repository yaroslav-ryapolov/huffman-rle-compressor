package test.com.harrycodeman.compression.hamming;

import com.harrycodeman.compression.hamming.HammingCode;
import org.junit.Test;

import static junit.framework.Assert.assertEquals;

public class HammingCodeTest {
    @Test
    public void testSimpleEncode() throws Exception {
        int toEncode = 1387;
        int expected = 22205;
        assertEquals(expected, HammingCode.encode7by4(toEncode));
    }
}
