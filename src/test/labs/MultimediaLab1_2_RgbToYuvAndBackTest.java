package test.labs;

import com.harrycodeman.compression.colorspaces.*;
import org.junit.Before;
import org.junit.Test;

public class MultimediaLab1_2_RgbToYuvAndBackTest {
//    private static final int WIDTH = 1600;
//    private static final int HEIGHT = 1195;
//    private static final String SOURCE_FILE_NAME = "./data/house.ppm";
//    private static final String SAVE_FILE_NAME = "./data/multimedia/house";

    private static final int WIDTH = 512;
    private static final int HEIGHT = 512;
    private static final String SOURCE_FILE_NAME = "./data/lenna.pnm";
    private static final String SAVE_FILE_NAME = "./data/multimedia/lenna";

    private Image rgbImage;

    @Before
    public void initializeClass() throws Exception {
        ImageReader rgbReader = new PpmImageReader(SOURCE_FILE_NAME);
        rgbImage = rgbReader.loadImage();
    }

    @Test
    public void convertToYuvSaveBackAndSave() throws Exception {
        ImageProcessingPipeline pipelineToYuv = new ImageProcessingPipeline(
                new FloatRgbToYuvImageProcessingStage(),
                new YuvImageWriter(SAVE_FILE_NAME + ".yuv")
        );
        pipelineToYuv.executeFor(rgbImage);
        Image loadedImage = new YuvImageReader(WIDTH, HEIGHT, SAVE_FILE_NAME + ".yuv").loadImage();

        ImageProcessingPipeline pipelineToRgb = new ImageProcessingPipeline(
                new FloatYuvToRgbImageProcessingStage(),
                new PpmImageWriter(SAVE_FILE_NAME + ".fromYuv422.pnm")
        );
        pipelineToRgb.executeFor(loadedImage);
    }

    @Test
    public void convertToYuvBackAndSave() throws Exception {
        new ImageProcessingPipeline(
                new FloatRgbToYuvImageProcessingStage(),
                new FloatYuvToRgbImageProcessingStage(),
                new PpmImageWriter(SAVE_FILE_NAME + ".throughFloat.pnm")
        ).executeFor(rgbImage);
    }

    @Test
    public void convertToYuvThroughIntegerBackAndSave() throws Exception {
        // TODO: implement IntegerYuvToRgb
        new ImageProcessingPipeline(
                new IntegerRgbToYuvImageProcessingStage(),
                new FloatYuvToRgbImageProcessingStage(),
                new PpmImageWriter(SAVE_FILE_NAME + ".throughInteger.pnm")
        ).executeFor(rgbImage);
    }

    @Test
    public void convertToYuvUToNegative128BackAndSave() throws Exception {
        new ImageProcessingPipeline(
                new FloatRgbToYuvImageProcessingStage(),
                new SomeComponentToConstImageProcessingStage(1, -128),
                new FloatYuvToRgbImageProcessingStage(),
                new PpmImageWriter(SAVE_FILE_NAME + ".withU(Cb)To-128.pnm")
        ).executeFor(rgbImage);
    }

    @Test
    public void convertToYuvVToNegative128BackAndSave() throws Exception {
        new ImageProcessingPipeline(
                new FloatRgbToYuvImageProcessingStage(),
                new SomeComponentToConstImageProcessingStage(2, -128),
                new FloatYuvToRgbImageProcessingStage(),
                new PpmImageWriter(SAVE_FILE_NAME + ".withV(Cr)To-128.pnm")
        ).executeFor(rgbImage);
    }

    @Test
    public void convertToYuvYToZeroBackAndSave() throws Exception {
        new ImageProcessingPipeline(
                new FloatRgbToYuvImageProcessingStage(),
                new SomeComponentToConstImageProcessingStage(0, 0),
                new FloatYuvToRgbImageProcessingStage(),
                new PpmImageWriter(SAVE_FILE_NAME + ".withYTo0.pnm")
        ).executeFor(rgbImage);
    }

    @Test
    public void convertToYuvUToZeroBackAndSave() throws Exception {
        new ImageProcessingPipeline(
                new FloatRgbToYuvImageProcessingStage(),
                new SomeComponentToConstImageProcessingStage(1, 0),
                new FloatYuvToRgbImageProcessingStage(),
                new PpmImageWriter(SAVE_FILE_NAME + ".withU(Cb)To0.pnm")
        ).executeFor(rgbImage);
    }

    @Test
    public void convertToYuvVToZeroBackAndSave() throws Exception {
        new ImageProcessingPipeline(
                new FloatRgbToYuvImageProcessingStage(),
                new SomeComponentToConstImageProcessingStage(2, 0),
                new FloatYuvToRgbImageProcessingStage(),
                new PpmImageWriter(SAVE_FILE_NAME + ".withV(Cr)To0.pnm")
        ).executeFor(rgbImage);
    }

    @Test
    public void convertToYuvUAndVToZeroBackAndSave() throws Exception {
        new ImageProcessingPipeline(
                new FloatRgbToYuvImageProcessingStage(),
                new SomeComponentToConstImageProcessingStage(1, 0),
                new SomeComponentToConstImageProcessingStage(2, 0),
                new FloatYuvToRgbImageProcessingStage(),
                new PpmImageWriter(SAVE_FILE_NAME + ".withU(Cb)AndV(Cr)To0.pnm")
        ).executeFor(rgbImage);
    }

    @Test
    public void convertToYuvYAndVToZeroBackAndSave() throws Exception {
        new ImageProcessingPipeline(
                new FloatRgbToYuvImageProcessingStage(),
                new SomeComponentToConstImageProcessingStage(0, 0),
                new SomeComponentToConstImageProcessingStage(2, 0),
                new FloatYuvToRgbImageProcessingStage(),
                new PpmImageWriter(SAVE_FILE_NAME + ".withYAndV(Cr)To0.pnm")
        ).executeFor(rgbImage);
    }

    @Test
    public void convertToYuvYAndUToZeroBackAndSave() throws Exception {
        new ImageProcessingPipeline(
                new FloatRgbToYuvImageProcessingStage(),
                new SomeComponentToConstImageProcessingStage(0, 0),
                new SomeComponentToConstImageProcessingStage(1, 0),
                new FloatYuvToRgbImageProcessingStage(),
                new PpmImageWriter(SAVE_FILE_NAME + ".withYAndU(Cb)To0.pnm")
        ).executeFor(rgbImage);
    }

    @Test
    public void convertToYuvYTo255BackAndSave() throws Exception {
        new ImageProcessingPipeline(
                new FloatRgbToYuvImageProcessingStage(),
                new SomeComponentToConstImageProcessingStage(0, 100),
                new FloatYuvToRgbImageProcessingStage(),
                new PpmImageWriter(SAVE_FILE_NAME + ".withYTo255.pnm")
        ).executeFor(rgbImage);
    }

    @Test
    public void convertToYuvUTo127BackAndSave() throws Exception {
        new ImageProcessingPipeline(
                new FloatRgbToYuvImageProcessingStage(),
                new SomeComponentToConstImageProcessingStage(1, 127),
                new FloatYuvToRgbImageProcessingStage(),
                new PpmImageWriter(SAVE_FILE_NAME + ".withU(Cb)To127.pnm")
        ).executeFor(rgbImage);
    }

    @Test
    public void convertToYuvVTo127BackAndSave() throws Exception {
        new ImageProcessingPipeline(
                new FloatRgbToYuvImageProcessingStage(),
                new SomeComponentToConstImageProcessingStage(2, 127),
                new FloatYuvToRgbImageProcessingStage(),
                new PpmImageWriter(SAVE_FILE_NAME + ".withV(Cr)To127.pnm")
        ).executeFor(rgbImage);
    }
}
