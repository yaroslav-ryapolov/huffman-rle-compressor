package test.com.harrycodeman;

import com.harrycodeman.StringRleCompressor;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class RleCompressorTest {

    @Before
    public void before() throws Exception {
    }

    @After
    public void after() throws Exception {
    }

    @Test
    public void testOneCharRepetitionCompress() throws Exception {
        String expectedResult = "3a";
        StringRleCompressor compressor = new StringRleCompressor("aaa");

        assertEquals(expectedResult, compressor.compress());
    }

    @Test
    public void testTwoCharRepetitionCompress() throws Exception {
        String expectedResult = "3a2b";
        StringRleCompressor compressor = new StringRleCompressor("aaabb");

        assertEquals(expectedResult, compressor.compress());
    }

    @Test
    public void testOneCharCompress() throws Exception {
        String expectedResult = "a";
        StringRleCompressor compressor = new StringRleCompressor("a");

        assertEquals(expectedResult, compressor.compress());
    }

    @Test
    public void testDifferentLastCharCompress() throws Exception {
        String expectedResult = "3a2bc";
        StringRleCompressor compressor = new StringRleCompressor("aaabbc");

        assertEquals(expectedResult, compressor.compress());
    }
} 
