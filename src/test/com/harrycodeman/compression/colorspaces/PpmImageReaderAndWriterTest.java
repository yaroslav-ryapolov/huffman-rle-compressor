package test.com.harrycodeman.compression.colorspaces;

import com.harrycodeman.compression.colorspaces.*;
import org.junit.Test;

import java.io.File;

import static junit.framework.Assert.assertEquals;

public class PpmImageReaderAndWriterTest {
    @Test
    public void testSaveAndLoadImage() throws Exception {
        try {
            PpmImageWriter writer = new PpmImageWriter("test.pnm");
            writer.saveImage(getTestImage());
            PpmImageReader reader = new PpmImageReader("test.pnm");
            SimplifiedImage image = reader.loadImage();
            assertEquals(outputImagePixelBlocks(image), getTestImage(), image);
        }
        finally {
            File f = new File("test.pnm");
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
        result.add(new Rgb888PixelBlock(226, 137, 125));
        result.add(new Rgb888PixelBlock(226, 137, 125));
        result.add(new Rgb888PixelBlock(223, 137, 133));
        result.add(new Rgb888PixelBlock(226, 137, 125));
        result.add(new Rgb888PixelBlock(226, 137, 125));
        result.add(new Rgb888PixelBlock(223, 137, 133));
        return result;
    }
}
