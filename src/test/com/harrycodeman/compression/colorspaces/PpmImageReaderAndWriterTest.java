package test.com.harrycodeman.compression.colorspaces;

import com.harrycodeman.compression.colorspaces.Image;
import com.harrycodeman.compression.colorspaces.PpmImageReader;
import com.harrycodeman.compression.colorspaces.PpmImageWriter;
import com.harrycodeman.compression.colorspaces.ThreeComponentPixelBlock;
import org.junit.Test;

import java.io.File;

import static junit.framework.Assert.assertEquals;

public class PpmImageReaderAndWriterTest {
    private static final String FILE_PATH = "./data/test.pnm";

    @Test
    public void testSaveAndLoadImage() throws Exception {
        try {
            PpmImageWriter writer = new PpmImageWriter(FILE_PATH);
            writer.saveImage(getTestImage());
            PpmImageReader reader = new PpmImageReader(FILE_PATH);
            Image image = reader.loadImage();
            assertEquals(getTestImage(), image);
        }
        finally {
            File f = new File(FILE_PATH);
            f.delete();
        }
    }

    private Image getTestImage() {
        return new Image(2, 3,
                new ThreeComponentPixelBlock(226, 13, 125),
                new ThreeComponentPixelBlock(226, 17, 12),
                new ThreeComponentPixelBlock(223, 127, 133),
                new ThreeComponentPixelBlock(226, 137, 25),
                new ThreeComponentPixelBlock(226, 12, 125),
                new ThreeComponentPixelBlock(223, 112, 33)
        );
    }
}
