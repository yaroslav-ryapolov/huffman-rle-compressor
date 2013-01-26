package test.com.harrycodeman.compression.hamming;

import com.harrycodeman.compression.hamming.HammingCode;
import com.harrycodeman.compression.hamming.UnrecoverableErrorsException;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import static junit.framework.Assert.assertEquals;

public class HammingCodeTest {
    @Test
    public void testEncode16by11() throws Exception {
        int toEncode = 1387;
        int expected = 44410;
        assertEquals(expected, HammingCode.encode16by11(toEncode));
    }

    @Test
    public void testDecode16by11Correct() throws Exception {
        int toDecode = 44410;
        int expected = 1387;
        assertEquals(expected, HammingCode.decode16by11(toDecode));
    }

    @Test
    public void testDecode16by11OneError() throws Exception {
        int toDecode = 44410;
        int expected = 1387;
        for (int i = 0; i < 16; i++) {
            int mask = 1 << i;
            int bitError = toDecode & mask;
            if (bitError > 0) {
                assertEquals(expected, HammingCode.decode16by11(toDecode & ~mask));
            }
            else {
                assertEquals(expected, HammingCode.decode16by11(toDecode | mask));
            }
        }
    }

    @Test(expected = UnrecoverableErrorsException.class)
    public void testDecode16by11TwoError() throws Exception {
        int toDecode = 28026; // (01 10110101111010) instead of (10 10110101111010)
        int expected = 1387;
        assertEquals(expected, HammingCode.decode16by11(toDecode));
    }
}
