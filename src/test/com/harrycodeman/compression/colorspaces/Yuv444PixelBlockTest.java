package test.com.harrycodeman.compression.colorspaces;

import com.harrycodeman.compression.colorspaces.Rgb888PixelBlock;
import com.harrycodeman.compression.colorspaces.Yuv444PixelBlock;
import org.junit.Test;

import static junit.framework.Assert.assertEquals;

public class Yuv444PixelBlockTest {
    @Test
    public void testBlackConvertToRgb() throws Exception {
        Yuv444PixelBlock pixelBlock = new Yuv444PixelBlock(0, 0, 0);
        assertEquals(new Rgb888PixelBlock(0, 0, 0), pixelBlock.convertToRgb());
    }

    @Test
    public void testWhiteConvertToRgb() throws Exception {
        Yuv444PixelBlock pixelBlock = new Yuv444PixelBlock(255, 0, 0);
        assertEquals(new Rgb888PixelBlock(255, 255, 255), pixelBlock.convertToRgb());
    }

    @Test
    public void testSomeYuvConvertToRgb() throws Exception {
        Yuv444PixelBlock pixelBlock = new Yuv444PixelBlock(33, 71, 29);
        // Accurate values in RGB: r = 66,05507; g = -11,85755; b = 177,27981;
        assertEquals(new Rgb888PixelBlock(66, 0, 177), pixelBlock.convertToRgb());
    }
}
