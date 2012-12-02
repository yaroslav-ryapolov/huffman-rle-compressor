package test.com.harrycodeman.compression.colorspaces;

import com.harrycodeman.compression.colorspaces.PpmImageReader;
import com.harrycodeman.compression.colorspaces.SimplifiedImage;
import org.junit.Test;

import static junit.framework.Assert.assertEquals;

public class PpmImageReaderTest {
    @Test
    public void testLoadImage() throws Exception {
        assertEquals(new SimplifiedImage(), PpmImageReader.loadImage("test.pnm"));
    }
}
