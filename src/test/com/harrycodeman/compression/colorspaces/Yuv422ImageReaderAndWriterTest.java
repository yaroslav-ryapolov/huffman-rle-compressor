package test.com.harrycodeman.compression.colorspaces;

import com.harrycodeman.compression.colorspaces.*;
import org.junit.Test;

import static junit.framework.Assert.assertEquals;

public class Yuv422ImageReaderAndWriterTest {
    @Test
    public void testSaveAndLoadImage() throws Exception {
        Yuv422ImageReaderAndWriter.saveImage(getTestImage(), "test.yuv");
        SimplifiedImage image = Yuv422ImageReaderAndWriter.loadImage(2, 3, "test.yuv");
        assertEquals(outputImagePixelBlocks(image), getExpectedImage(), image);
    }

    private String outputImagePixelBlocks(SimplifiedImage image) {
        String result = "";
        for (IPixelBlock b : image.pixelBlocks) {
            result += b;
        }
        return result;
    }

    private SimplifiedImage getTestImage() {
        SimplifiedImage result = new SimplifiedImage(2, 3);
        result.pixelBlocks.add(new Yuv444PixelBlock(139, 212, 32));
        result.pixelBlocks.add(new Yuv444PixelBlock(154, 203, 207));
        result.pixelBlocks.add(new Yuv444PixelBlock(20, 20, 20));
        result.pixelBlocks.add(new Yuv444PixelBlock(12, 24, 33));
        result.pixelBlocks.add(new Yuv444PixelBlock(32, 33, 2));
        result.pixelBlocks.add(new Yuv444PixelBlock(255, 255, 255));
        return result;
    }

    private SimplifiedImage getExpectedImage() {
        SimplifiedImage result = new SimplifiedImage(2, 3);
        result.pixelBlocks.add(new Yuv444PixelBlock(139, 212, 32));
        result.pixelBlocks.add(new Yuv444PixelBlock(154, 212, 32));
        result.pixelBlocks.add(new Yuv444PixelBlock(20, 20, 20));
        result.pixelBlocks.add(new Yuv444PixelBlock(12, 20, 20));
        result.pixelBlocks.add(new Yuv444PixelBlock(32, 33, 2));
        result.pixelBlocks.add(new Yuv444PixelBlock(255, 33, 2));
        return result;
    }
}
