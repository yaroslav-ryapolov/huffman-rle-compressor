package test.com.harrycodeman.rle;

import com.harrycodeman.rle.BytesBlock;
import com.harrycodeman.rle.BytesBlocksToHexStringConverter;
import com.harrycodeman.rle.DifferentBytesBlock;
import com.harrycodeman.rle.SameBytesBlock;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Collection;

import static junit.framework.Assert.assertEquals;

public class BytesBlocksToHexStringConverterTest extends BytesBlocksToHexStringConverter {
    @Test
    public void testOneSameBytesBlockConvert() throws Exception {
        Collection<BytesBlock> blocks = new ArrayList<BytesBlock>();
        blocks.add(new SameBytesBlock('a', 3));
        assertEquals("(3: 02) 61  |  61 61 61", BytesBlocksToHexStringConverter.convert(blocks));
    }

    @Test
    public void testOneDifferentBytesBlockConvert() throws Exception {
        Collection<BytesBlock> blocks = new ArrayList<BytesBlock>();
        blocks.add(new DifferentBytesBlock("abc"));
        assertEquals("(-3: FD) 61 62 63  |  61 62 63", BytesBlocksToHexStringConverter.convert(blocks));
    }

    @Test
    public void testBlocksCollectionConvert() throws Exception {
        Collection<BytesBlock> blocks = new ArrayList<BytesBlock>();
        blocks.add(new DifferentBytesBlock("abc"));
        blocks.add(new SameBytesBlock('a', 3));
        blocks.add(new DifferentBytesBlock("bc"));
        assertEquals(
            "(-3: FD) 61 62 63  |  61 62 63\n" +
            "(3: 02) 61  |  61 61 61\n" +
            "(-2: FE) 62 63  |  62 63",
            BytesBlocksToHexStringConverter.convert(blocks));
    }
}
