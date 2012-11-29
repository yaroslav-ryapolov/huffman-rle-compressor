package test.com.harrycodeman.compression.rle;

import com.harrycodeman.compression.rle.DifferentBytesBlock;
import com.harrycodeman.compression.rle.SameBytesBlock;
import org.junit.Test;

import static junit.framework.Assert.assertEquals;
import static junit.framework.Assert.assertFalse;

public class DifferentBytesBlockTest {
    @Test
    public void testEqualsForEqualObjects() throws Exception {
        assertEquals(new DifferentBytesBlock("abc"), new DifferentBytesBlock("abc"));
    }

    @Test
    public void testEqualsForDifferentLengthObjects() throws Exception {
        assertFalse(new DifferentBytesBlock("abc").equals(new DifferentBytesBlock("abcd")));
    }

    @Test
    public void testEqualsForDifferentSymbolsObjects() throws Exception {
        assertFalse(new DifferentBytesBlock("abc").equals(new DifferentBytesBlock("abd")));
    }

    @Test
    public void testEqualsForDifferentTypesObjects() throws Exception {
        assertFalse(new DifferentBytesBlock("abc").equals(new SameBytesBlock('a', 3)));
    }
}
