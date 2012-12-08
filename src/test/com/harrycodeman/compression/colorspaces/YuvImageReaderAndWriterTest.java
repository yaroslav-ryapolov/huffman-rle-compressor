package test.com.harrycodeman.compression.colorspaces;

import com.harrycodeman.compression.colorspaces.*;
import org.junit.Test;

import java.io.File;

import static junit.framework.Assert.assertEquals;

public class YuvImageReaderAndWriterTest {
    private static final String FILE_PATH = "./data/test.yuv";

    @Test
    public void testSaveAndLoadImage() throws Exception {
        try {
            YuvImageWriter writer = new YuvImageWriter(FILE_PATH);
            writer.saveImage(getTestImage());
            YuvImageReader reader = new YuvImageReader(2, 3, FILE_PATH);
            Image image = reader.loadImage();
            assertEquals(getExpectedImage(), image);
        }
        finally {
            File f = new File(FILE_PATH);
            f.delete();
        }
    }

    private Image getTestImage() {
        return new Image(2, 3,
                new ThreeComponentPixelBlock(139, 84, 32),
                new ThreeComponentPixelBlock(154, 75, 79),
                new ThreeComponentPixelBlock(20, 20, -20),
                new ThreeComponentPixelBlock(12, 24, 33),
                new ThreeComponentPixelBlock(32, -33, 2),
                new ThreeComponentPixelBlock(255, 128, 128)
        );
    }

    private Image getExpectedImage() {
        return new Image(2, 3,
                new ThreeComponentPixelBlock(139, 84, 32),
                new ThreeComponentPixelBlock(154, 84, 32),
                new ThreeComponentPixelBlock(20, 20, -20),
                new ThreeComponentPixelBlock(12, 20, -20),
                new ThreeComponentPixelBlock(32, -33, 2),
                new ThreeComponentPixelBlock(255, -33, 2)
        );
    }
}
