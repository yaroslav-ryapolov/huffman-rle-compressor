package test.labs;

import com.harrycodeman.compression.colorspaces.*;
import org.junit.Before;
import org.junit.Test;

public class MultimediaLab4_DCT {
    private static final String SOURCE_FILE_NAME = "./data/lenna.pnm";
    private static final String SAVE_FILE_NAME = "./data/multimedia/lenna";

    private Image rgbImage;

    @Before
    public void initializeClass() throws Exception {
        ImageReader rgbReader = new PpmImageReader(SOURCE_FILE_NAME);
        rgbImage = rgbReader.loadImage();
    }

    @Test
    public void fdctThenIdct() throws Exception {
        new ImageProcessingPipeline(
                new FloatRgbToYuvImageProcessingStage(),
                new ForwardDctImageProcessingStage(),
                new InverseDctImageProcessingStage(),
                new FloatYuvToRgbImageProcessingStage(),
                new PpmImageWriter(SAVE_FILE_NAME + ".dctQuantizeAndBack.pnm")
        ).executeFor(rgbImage);
    }
}
