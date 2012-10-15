package test.com.harrycodeman;

import com.harrycodeman.DifferentSymbolsStringRleCompressor;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static junit.framework.Assert.assertEquals;

public class DifferentSymbolsStringRleCompressorTest {
    @Before
    public void setUp() throws Exception {
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void testOneCharCompress() {
        String expectedResult = "-1a";
        DifferentSymbolsStringRleCompressor compressor = new DifferentSymbolsStringRleCompressor("a");

        assertEquals(expectedResult, compressor.compress());
    }
}
