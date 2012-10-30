package test.com.harrycodeman;

import com.harrycodeman.DifferentSymbolsStringRleCompressor;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static junit.framework.Assert.assertEquals;

public class DifferentSymbolsStringRleCompressorTest {
    @Test
    public void testOneCharCompress() {
        String expectedResult = "-1a";
        DifferentSymbolsStringRleCompressor compressor = new DifferentSymbolsStringRleCompressor("a");

        assertEquals(expectedResult, compressor.compress());
    }

    @Test
    public void testSequenceOfDifferentCharCompress() {
        String expectedResult = "-3abc";
        DifferentSymbolsStringRleCompressor compressor = new DifferentSymbolsStringRleCompressor("abc");

        assertEquals(expectedResult, compressor.compress());
    }

    @Test
    public void testSequenceOfSameCharCompress() {
        String expectedResult = "-1a-1a-1a";
        DifferentSymbolsStringRleCompressor compressor = new DifferentSymbolsStringRleCompressor("aaa");

        assertEquals(expectedResult, compressor.compress());
    }

    @Test
    public void testLastTwoSameCharCompress() {
        String expectedResult = "-3abc-1c";
        DifferentSymbolsStringRleCompressor compressor = new DifferentSymbolsStringRleCompressor("abcc");

        assertEquals(expectedResult, compressor.compress());
    }
}
