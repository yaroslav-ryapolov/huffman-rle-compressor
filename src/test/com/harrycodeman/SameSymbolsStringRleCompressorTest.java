package test.com.harrycodeman;

import com.harrycodeman.SameSymbolsStringRleCompressor;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class SameSymbolsStringRleCompressorTest {

    @Before
    public void before() throws Exception {
    }

    @After
    public void after() throws Exception {
    }

    @Test
    public void testOneCharRepetitionCompress() throws Exception {
        String expectedResult = "3a";
        SameSymbolsStringRleCompressor compressor = new SameSymbolsStringRleCompressor("aaa");

        assertEquals(expectedResult, compressor.compress());
    }

    @Test
    public void testTwoCharRepetitionCompress() throws Exception {
        String expectedResult = "3a2b";
        SameSymbolsStringRleCompressor compressor = new SameSymbolsStringRleCompressor("aaabb");

        assertEquals(expectedResult, compressor.compress());
    }

    @Test
    public void testOneCharCompress() throws Exception {
        String expectedResult = "a";
        SameSymbolsStringRleCompressor compressor = new SameSymbolsStringRleCompressor("a");

        assertEquals(expectedResult, compressor.compress());
    }

    @Test
    public void testDifferentLastCharCompress() throws Exception {
        String expectedResult = "3a2bc";
        SameSymbolsStringRleCompressor compressor = new SameSymbolsStringRleCompressor("aaabbc");

        assertEquals(expectedResult, compressor.compress());
    }
}
