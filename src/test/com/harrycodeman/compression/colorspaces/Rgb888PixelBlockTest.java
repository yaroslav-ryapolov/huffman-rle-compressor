package test.com.harrycodeman.compression.colorspaces;

import com.harrycodeman.compression.colorspaces.Rgb888PixelBlock;
import com.harrycodeman.compression.colorspaces.Yuv444PixelBlock;
import org.junit.Test;

import static junit.framework.Assert.assertEquals;

public class Rgb888PixelBlockTest {
    @Test
    public void testWhiteConvertToYuv444() throws Exception {
        Rgb888PixelBlock pixelBlock = new Rgb888PixelBlock(255, 255, 255);
        assertEquals(new Yuv444PixelBlock(255, 0, 0), pixelBlock.convertToYuv444());
    }

    @Test
    public void testBlackConvertToYuv444() {
        Rgb888PixelBlock pixelBlock = new Rgb888PixelBlock(0, 0, 0);
        assertEquals(new Yuv444PixelBlock(0, 0, 0), pixelBlock.convertToYuv444());
    }

    @Test
    public void testSomeRgbConvertToYuv444() {
        Rgb888PixelBlock pixelBlock = new Rgb888PixelBlock(240, 37, 142);
        // Accurate values in YUV: y = 109,667; u = 15,91298; v = 114,34395;
        assertEquals(new Yuv444PixelBlock(109, 15, 114), pixelBlock.convertToYuv444());
    }
}
