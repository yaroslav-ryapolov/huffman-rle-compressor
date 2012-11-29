package test.com.harrycodeman.compression.rle;

import com.harrycodeman.compression.ICompressInput;
import com.harrycodeman.compression.MemoryCompressInput;
import com.harrycodeman.compression.rle.*;
import org.junit.Test;

import static org.junit.Assert.assertArrayEquals;

public class BytesRleCompressorTest {
    @Test
    public void testOneCharRepetitionCompress() throws Exception {
        BytesRleCompressor compressor = getCompressorForString("aaa");
        BytesBlock[] expected = new BytesBlock[] { new SameBytesBlock('a', 3) };
        assertArrayEquals(expected, compressor.compress().toArray());
    }

    @Test
    public void testTwoCharRepetitionCompress() throws Exception {
        BytesRleCompressor compressor = getCompressorForString("aaabb");
        BytesBlock[] expected = new BytesBlock[] {
            new SameBytesBlock('a', 3),
            new SameBytesBlock('b', 2)
        };
        assertArrayEquals(expected, compressor.compress().toArray());
    }

    @Test
    public void testOneCharCompress() throws Exception {
        BytesRleCompressor compressor = getCompressorForString("a");
        BytesBlock[] expected = new BytesBlock[] { new SameBytesBlock('a', 1) };
        assertArrayEquals(expected, compressor.compress().toArray());
    }

    @Test
    public void testDifferentLastCharCompress() throws Exception {
        BytesRleCompressor compressor = getCompressorForString("aaabbc");
        BytesBlock[] expected = new BytesBlock[] {
            new SameBytesBlock('a', 3),
            new SameBytesBlock('b', 2),
            new SameBytesBlock('c', 1)
        };
        assertArrayEquals(expected, compressor.compress().toArray());
    }

    @Test
    public void testDifferentCharsCompress() throws Exception {
        BytesRleCompressor compressor = getCompressorForString("abcabc");
        BytesBlock[] expected = new BytesBlock[] { new DifferentBytesBlock("abcabc") };
        assertArrayEquals(expected, compressor.compress().toArray());
    }

    @Test
    public void testSameDifferentSameCharsCompress() throws Exception {
        BytesRleCompressor compressor = getCompressorForString("aaabcdeee");
        BytesBlock[] expected = new BytesBlock[] {
            new SameBytesBlock((int)'a', 3),
            new DifferentBytesBlock("bcd"),
            new SameBytesBlock((int)'e', 3)
        };
        assertArrayEquals(expected, compressor.compress().toArray());
    }

    @Test
    public void test130SameCharsCompress() throws Exception {
        BytesRleCompressor compressor = getCompressorForString("aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa");
        BytesBlock[] expected = new BytesBlock[] {
            new SameBytesBlock('a', 128),
            new SameBytesBlock('a', 2)
        };
        assertArrayEquals(expected, compressor.compress().toArray());
    }

    @Test
    public void test130DifferentCharsCompress() throws Exception {
        BytesRleCompressor compressor = getCompressorForString("qwertyuiopqwertyuiopqwertyuiopqwertyuiopqwertyuiopqwertyuiopqwertyuiopqwertyuiopqwertyuiopqwertyuiopqwertyuiopqwertyuiopqwertyuiop");
        BytesBlock[] expected = new BytesBlock[] {
            new DifferentBytesBlock("qwertyuiopqwertyuiopqwertyuiopqwertyuiopqwertyuiopqwertyuiopqwertyuiopqwertyuiopqwertyuiopqwertyuiopqwertyuiopqwertyuiopqwertyui"),
            new DifferentBytesBlock("op"),
        };
        assertArrayEquals(expected, compressor.compress().toArray());
    }

    private BytesRleCompressor getCompressorForString(String s) {
        ICompressInput stream = new MemoryCompressInput(s);
        return new BytesRleCompressor(stream);
    }
}
