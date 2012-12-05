package test.com.harrycodeman.compression.colorspaces;

import com.harrycodeman.compression.colorspaces.IPixelBlock;
import com.harrycodeman.compression.colorspaces.PpmImageReaderAndWriter;
import com.harrycodeman.compression.colorspaces.Rgb888PixelBlock;
import com.harrycodeman.compression.colorspaces.SimplifiedImage;
import org.junit.Test;

import static junit.framework.Assert.assertEquals;

public class PpmImageReaderAndWriterTest {
    @Test
    public void testSaveAndLoadImage() throws Exception {
        PpmImageReaderAndWriter.saveImage(getTestImage(), "test.pnm");
        SimplifiedImage image = PpmImageReaderAndWriter.loadImage("test.pnm");
        assertEquals(outputImagePixelBlocks(image), getTestImage(), image);
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
        result.add(new Rgb888PixelBlock(226, 137, 125));
        result.add(new Rgb888PixelBlock(226, 137, 125));
        result.add(new Rgb888PixelBlock(223, 137, 133));
        result.add(new Rgb888PixelBlock(226, 137, 125));
        result.add(new Rgb888PixelBlock(226, 137, 125));
        result.add(new Rgb888PixelBlock(223, 137, 133));
        return result;
    }
}
