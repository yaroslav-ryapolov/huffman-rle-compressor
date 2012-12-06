package test.com.harrycodeman.compression.colorspaces;

import com.harrycodeman.compression.colorspaces.*;
import org.junit.Before;
import org.junit.Test;

public class MultimediaLab1_2_RgbToYuvAndBackTest {
    private static final int WIDTH = 512;
    private static final int HEIGHT = 512;
    private static final String SOURCE_FILE_NAME = "./data/lenna.pnm";
    private static final String SAVE_FILE_NAME = "./data/multimedia/lenna";

    private SimplifiedImage rgbImage;

    @Before
    public void initializeClass() throws Exception {
        ImageReader rgbReader = new PpmImageReader(SOURCE_FILE_NAME);
        rgbImage = rgbReader.loadImage();
    }

    @Test
    public void convertToYuvSaveBackAndSave() throws Exception {
        SimplifiedImage yuvImage = rgbImage.convert(new Rgb888ToYuv444PixelBlockConverter());
        new Yuv422ImageWriter(SAVE_FILE_NAME + ".yuv").saveImage(yuvImage);
        yuvImage = new Yuv422ImageReader(WIDTH, HEIGHT, SAVE_FILE_NAME + ".yuv").loadImage();
        SimplifiedImage backRgbImage = yuvImage.convert(new Yuv444ToRgb888PixelBlockConverter());
        new PpmImageWriter(SAVE_FILE_NAME + ".backFromYuv422.pnm").saveImage(backRgbImage);
    }

    @Test
    public void convertToYuvBackAndSave() throws Exception {
        SimplifiedImage yuvImage = rgbImage.convert(new Rgb888ToYuv444PixelBlockConverter());
        SimplifiedImage backRgbImage = yuvImage.convert(new Yuv444ToRgb888PixelBlockConverter());
        new PpmImageWriter(SAVE_FILE_NAME + ".backFromYuv444.pnm").saveImage(backRgbImage);
    }

    @Test
    public void convertToYuvThroughIntegerBackAndSave() throws Exception {
        SimplifiedImage yuvImage = rgbImage.convert(new Rgb888ToYuv444IntegerPixelBlockConverter(false));
        SimplifiedImage backRgbImage = yuvImage.convert(new Yuv444ToRgb888PixelBlockConverter());
        new PpmImageWriter(SAVE_FILE_NAME + ".backFromYuv444ThroughInteger.pnm").saveImage(backRgbImage);
    }

    @Test
    public void convertToYuvThroughIntegerWithShiftBackAndSave() throws Exception {
        SimplifiedImage yuvImage = rgbImage.convert(new Rgb888ToYuv444IntegerPixelBlockConverter(true));
        SimplifiedImage backRgbImage = yuvImage.convert(new Yuv444ToRgb888PixelBlockConverter());
        new PpmImageWriter(SAVE_FILE_NAME + ".backFromYuv444ThroughIntegerWithShift.pnm").saveImage(backRgbImage);
    }

    @Test
    public void convertToYuvUToNegative128BackAndSave() throws Exception {
        SimplifiedImage yuvImage = rgbImage.convert(new Rgb888ToYuv444PixelBlockConverter());
        SimplifiedImage yuvWithUToZeroImage = yuvImage.convert(new YuvUToConstPixelBlockConverter(-128));
        SimplifiedImage backRgbImage = yuvWithUToZeroImage.convert(new Yuv444ToRgb888PixelBlockConverter());
        new PpmImageWriter(SAVE_FILE_NAME + ".backFromYuv444WithUTo-128.pnm").saveImage(backRgbImage);
    }

    @Test
    public void convertToYuvVToNegative128BackAndSave() throws Exception {
        SimplifiedImage yuvImage = rgbImage.convert(new Rgb888ToYuv444PixelBlockConverter());
        SimplifiedImage yuvWithVToZeroImage = yuvImage.convert(new YuvVToConstPixelBlockConverter(-128));
        SimplifiedImage backRgbImage = yuvWithVToZeroImage.convert(new Yuv444ToRgb888PixelBlockConverter());
        new PpmImageWriter(SAVE_FILE_NAME + ".backFromYuv444WithVTo-128.pnm").saveImage(backRgbImage);
    }

    @Test
    public void convertToYuvYToZeroBackAndSave() throws Exception {
        SimplifiedImage yuvImage = rgbImage.convert(new Rgb888ToYuv444PixelBlockConverter());
        SimplifiedImage yuvWithYToZeroImage = yuvImage.convert(new YuvYToConstPixelBlockConverter(0));
        SimplifiedImage backRgbImage = yuvWithYToZeroImage.convert(new Yuv444ToRgb888PixelBlockConverter());
        new PpmImageWriter(SAVE_FILE_NAME + ".backFromYuv444WithYToZero.pnm").saveImage(backRgbImage);
    }

    @Test
    public void convertToYuvUToZeroBackAndSave() throws Exception {
        SimplifiedImage yuvImage = rgbImage.convert(new Rgb888ToYuv444PixelBlockConverter());
        SimplifiedImage yuvWithUToZeroImage = yuvImage.convert(new YuvUToConstPixelBlockConverter(0));
        SimplifiedImage backRgbImage = yuvWithUToZeroImage.convert(new Yuv444ToRgb888PixelBlockConverter());
        new PpmImageWriter(SAVE_FILE_NAME + ".backFromYuv444WithUToZero.pnm").saveImage(backRgbImage);
    }

    @Test
    public void convertToYuvVToZeroBackAndSave() throws Exception {
        SimplifiedImage yuvImage = rgbImage.convert(new Rgb888ToYuv444PixelBlockConverter());
        SimplifiedImage yuvWithVToZeroImage = yuvImage.convert(new YuvVToConstPixelBlockConverter(0));
        SimplifiedImage backRgbImage = yuvWithVToZeroImage.convert(new Yuv444ToRgb888PixelBlockConverter());
        new PpmImageWriter(SAVE_FILE_NAME + ".backFromYuv444WithVToZero.pnm").saveImage(backRgbImage);
    }

    @Test
    public void convertToYuvUToZeroVToZeroBackAndSave() throws Exception {
        SimplifiedImage yuvImage = rgbImage.convert(new Rgb888ToYuv444PixelBlockConverter());
        SimplifiedImage yuvWithUToZeroImage = yuvImage.convert(new YuvUToConstPixelBlockConverter(0));
        SimplifiedImage yuvWithUAndVToZeroImage = yuvWithUToZeroImage.convert(new YuvVToConstPixelBlockConverter(0));
        SimplifiedImage backRgbImage = yuvWithUAndVToZeroImage.convert(new Yuv444ToRgb888PixelBlockConverter());
        new PpmImageWriter(SAVE_FILE_NAME + ".backFromYuv444WithUToZeroAndVToZero.pnm").saveImage(backRgbImage);
    }

    @Test
    public void convertToYuvYTo255BackAndSave() throws Exception {
        SimplifiedImage yuvImage = rgbImage.convert(new Rgb888ToYuv444PixelBlockConverter());
        SimplifiedImage yuvWithYToZeroImage = yuvImage.convert(new YuvYToConstPixelBlockConverter(255));
        SimplifiedImage backRgbImage = yuvWithYToZeroImage.convert(new Yuv444ToRgb888PixelBlockConverter());
        new PpmImageWriter(SAVE_FILE_NAME + ".backFromYuv444WithYTo255.pnm").saveImage(backRgbImage);
    }

    @Test
    public void convertToYuvUTo128BackAndSave() throws Exception {
        SimplifiedImage yuvImage = rgbImage.convert(new Rgb888ToYuv444PixelBlockConverter());
        SimplifiedImage yuvWithUToZeroImage = yuvImage.convert(new YuvUToConstPixelBlockConverter(128));
        SimplifiedImage backRgbImage = yuvWithUToZeroImage.convert(new Yuv444ToRgb888PixelBlockConverter());
        new PpmImageWriter(SAVE_FILE_NAME + ".backFromYuv444WithUTo128.pnm").saveImage(backRgbImage);
    }

    @Test
    public void convertToYuvVTo128BackAndSave() throws Exception {
        SimplifiedImage yuvImage = rgbImage.convert(new Rgb888ToYuv444PixelBlockConverter());
        SimplifiedImage yuvWithVToZeroImage = yuvImage.convert(new YuvVToConstPixelBlockConverter(128));
        SimplifiedImage backRgbImage = yuvWithVToZeroImage.convert(new Yuv444ToRgb888PixelBlockConverter());
        new PpmImageWriter(SAVE_FILE_NAME + ".backFromYuv444WithVTo128.pnm").saveImage(backRgbImage);
    }
}
