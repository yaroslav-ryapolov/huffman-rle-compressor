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
        result.pixelBlocks.add(new Rgb888PixelBlock(226, 137, 125).convertToYuv444());
        result.pixelBlocks.add(new Rgb888PixelBlock(226, 137, 125).convertToYuv444());
        result.pixelBlocks.add(new Rgb888PixelBlock(223, 137, 133).convertToYuv444());
        result.pixelBlocks.add(new Rgb888PixelBlock(226, 137, 125).convertToYuv444());
        result.pixelBlocks.add(new Rgb888PixelBlock(226, 137, 125).convertToYuv444());
        result.pixelBlocks.add(new Rgb888PixelBlock(223, 137, 133).convertToYuv444());
        return result;
    }

    private SimplifiedImage getExpectedImage() {
        SimplifiedImage result = new SimplifiedImage(2, 3);
        result.pixelBlocks.add(new Rgb888PixelBlock(226, 137, 125).convertToYuv444());
        result.pixelBlocks.add(new Rgb888PixelBlock(226, 137, 125).convertToYuv444());
        result.pixelBlocks.add(new Rgb888PixelBlock(223, 137, 125).convertToYuv444());
        result.pixelBlocks.add(new Rgb888PixelBlock(226, 137, 125).convertToYuv444());
        result.pixelBlocks.add(new Rgb888PixelBlock(226, 137, 133).convertToYuv444());
        result.pixelBlocks.add(new Rgb888PixelBlock(223, 137, 133).convertToYuv444());
        return result;
    }

    @Test
    public void testSaveAndLoadImage1() throws Exception {
        SimplifiedImage image = PpmImageReaderAndWriter.loadImage("lenna.pnm");
        SimplifiedImage result = new SimplifiedImage(image.getWidth(), image.getHeight());
        for (IPixelBlock b : image.pixelBlocks) {
            Rgb888PixelBlock typedB = (Rgb888PixelBlock)b;
            result.pixelBlocks.add(((Rgb888PixelBlock) b).convertToYuv444());
        }
        Yuv422ImageReaderAndWriter.saveImage(result, "lenna.yuv");
    }
}
