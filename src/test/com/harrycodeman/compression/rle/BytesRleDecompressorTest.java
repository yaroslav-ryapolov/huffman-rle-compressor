package test.com.harrycodeman.compression.rle;

import com.harrycodeman.compression.MemoryCompressInput;
import com.harrycodeman.compression.rle.BytesRleDecompressor;
import org.junit.Test;

import static org.junit.Assert.assertArrayEquals;

public class BytesRleDecompressorTest {
    @Test
    public void testDecompress() throws Exception {
        BytesRleDecompressor decompressor = new BytesRleDecompressor(new MemoryCompressInput(
                new byte[] { 10, 100, -2, 102, 101 }
        ));
        assertArrayEquals(
                new Object[] {
                    100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 102, 101
                },
                decompressor.decompress().toArray()
        );
    }
}
