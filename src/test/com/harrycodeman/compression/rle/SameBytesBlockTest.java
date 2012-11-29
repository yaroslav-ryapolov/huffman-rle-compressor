package test.com.harrycodeman.compression.rle;

import com.harrycodeman.compression.rle.DifferentBytesBlock;
import com.harrycodeman.compression.rle.SameBytesBlock;
import org.junit.Test;

import static junit.framework.Assert.assertEquals;
import static junit.framework.Assert.assertFalse;

public class SameBytesBlockTest {
    @Test
    public void testEqualsForEqualObjects() throws Exception {
        assertEquals(new SameBytesBlock((int)'a', 123), new SameBytesBlock((int)'a', 123));
    }

    @Test
    public void testEqualsForDifferentLengthObjects() throws Exception {
        assertFalse(new SameBytesBlock((int) 'a', 123).equals(new SameBytesBlock((int) 'a', 12)));
    }

    @Test
    public void testEqualsForDifferentSymbolObjects() throws Exception {
        assertFalse(new SameBytesBlock((int) 'a', 123).equals(new SameBytesBlock((int) 'b', 123)));
    }

    @Test
    public void testEqualsForDifferentTypeObjects() throws Exception {
        assertFalse(new SameBytesBlock((int) 'a', 123).equals(new DifferentBytesBlock('a', 'b')));
    }
}
