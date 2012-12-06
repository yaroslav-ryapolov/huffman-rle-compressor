package test.com.harrycodeman.compression.colorspaces;

import com.harrycodeman.compression.colorspaces.*;
import org.junit.Test;

import java.io.File;

import static junit.framework.Assert.assertEquals;

public class Yuv422ImageReaderAndWriterTest {
    @Test
    public void testSaveAndLoadImage() throws Exception {
        try {
            Yuv422ImageWriter writer = new Yuv422ImageWriter("test.yuv");
            writer.saveImage(getTestImage());
            Yuv422ImageReader reader = new Yuv422ImageReader(2, 3, "test.yuv");
            SimplifiedImage image = reader.loadImage();
            assertEquals(outputImagePixelBlocks(image), getExpectedImage(), image);
        }
        finally {
            File f = new File("test.yuv");
            f.delete();
        }
    }

    private String outputImagePixelBlocks(SimplifiedImage image) {
        String result = "";
        for (IPixelBlock b : image) {
            result += b;
        }
        return result;
    }

    private SimplifiedImage getTestImage() {
        SimplifiedImage result = new SimplifiedImage(2, 3);
        result.add(new Yuv444PixelBlock(139, 84, 32));
        result.add(new Yuv444PixelBlock(154, 75, 79));
        result.add(new Yuv444PixelBlock(20, 20, 20));
        result.add(new Yuv444PixelBlock(12, 24, 33));
        result.add(new Yuv444PixelBlock(32, 33, 2));
        result.add(new Yuv444PixelBlock(255, 128, 128));
        return result;
    }

    private SimplifiedImage getExpectedImage() {
        SimplifiedImage result = new SimplifiedImage(2, 3);
        result.add(new Yuv444PixelBlock(139, 84, 32));
        result.add(new Yuv444PixelBlock(154, 84, 32));
        result.add(new Yuv444PixelBlock(20, 20, 20));
        result.add(new Yuv444PixelBlock(12, 20, 20));
        result.add(new Yuv444PixelBlock(32, 33, 2));
        result.add(new Yuv444PixelBlock(255, 33, 2));
        return result;
    }
}
