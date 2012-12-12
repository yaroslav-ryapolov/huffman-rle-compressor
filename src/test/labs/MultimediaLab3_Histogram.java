package test.labs;

import com.harrycodeman.compression.colorspaces.*;
import org.junit.Before;
import org.junit.Test;

public class MultimediaLab3_Histogram {
    private static final String SOURCE_FILE_NAME = "./data/lenna.pnm";
    private static final String SAVE_FILE_NAME = "./data/multimedia/lenna";

    private Image rgbImage;

    @Before
    public void initializeClass() throws Exception {
        ImageReader rgbReader = new PpmImageReader(SOURCE_FILE_NAME);
        rgbImage = rgbReader.loadImage();
    }

    @Test
    public void buildHistogram() throws Exception {
        new ImageProcessingPipeline(
                new FloatRgbToYuvImageProcessingStage(),
                new BuildHistogramImageProcessingStage(),
                new FloatYuvToRgbImageProcessingStage(),
                new PpmImageWriter(SAVE_FILE_NAME + ".histogram.pnm")
        ).executeFor(rgbImage);
    }
}
