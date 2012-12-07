package test.com.harrycodeman.compression.rle;

import com.harrycodeman.compression.rle.BytesBlock;
import com.harrycodeman.compression.rle.BytesBlockCompressInput;
import com.harrycodeman.compression.rle.DifferentBytesBlock;
import com.harrycodeman.compression.rle.SameBytesBlock;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertArrayEquals;

public class BytesBlockCompressInputTest {
    @Test
    public void testIterator() throws Exception {
        BytesBlockCompressInput compressInput = new BytesBlockCompressInput(
                new BytesBlock[] {
                    new SameBytesBlock(1, 3),
                    new DifferentBytesBlock("abc")
                }
        );
        List<Integer> result = new ArrayList<Integer>();
        for (int b : compressInput) {
            result.add(b);
        }
        assertArrayEquals(new Object[] { 2, 1, -3, 97, 98, 99 }, result.toArray());
    }
}
