package test.com.harrycodeman;

import com.harrycodeman.StringRleCompressor;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class StringRleCompressorTest {
    @Test
    public void testOneCharRepetitionCompress() throws Exception {
        StringRleCompressor compressor = new StringRleCompressor("aaa");
        assertEquals("3a", compressor.compress());
    }

    @Test
    public void testTwoCharRepetitionCompress() throws Exception {
        StringRleCompressor compressor = new StringRleCompressor("aaabb");
        assertEquals("3a2b", compressor.compress());
    }

    @Test
    public void testOneCharCompress() throws Exception {
        StringRleCompressor compressor = new StringRleCompressor("a");
        assertEquals("1a", compressor.compress());
    }

    @Test
    public void testDifferentLastCharCompress() throws Exception {
        StringRleCompressor compressor = new StringRleCompressor("aaabbc");
        assertEquals("3a2b1c", compressor.compress());
    }

    @Test
    public void testDifferentCharsCompress() throws Exception {
        StringRleCompressor compressor = new StringRleCompressor("abcabc");
        assertEquals("-6abcabc", compressor.compress());
    }

    @Test
    public void testSameDifferentSameCharsCompress() throws Exception {
        StringRleCompressor compressor = new StringRleCompressor("aaabcdeee");
        assertEquals("3a-3bcd3e", compressor.compress());
    }
}
