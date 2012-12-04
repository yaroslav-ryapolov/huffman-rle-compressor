package test.com.harrycodeman.compression.colorspaces;

import com.harrycodeman.compression.colorspaces.Rgb888PixelBlock;
import com.harrycodeman.compression.colorspaces.Yuv444PixelBlock;
import com.harrycodeman.compression.colorspaces.Yuv444ToRgb888PixelBlockConverter;
import org.junit.Test;

import static junit.framework.Assert.assertEquals;

public class Yuv444PixelBlockTest {
    @Test
    public void testBlackConvert() throws Exception {
        assertEquals(new Rgb888PixelBlock(0, 0, 0),
                Yuv444ToRgb888PixelBlockConverter.convert(new Yuv444PixelBlock(0, 0, 0)));
    }

    @Test
    public void testWhiteConvert() throws Exception {
        assertEquals(new Rgb888PixelBlock(255, 255, 255),
                Yuv444ToRgb888PixelBlockConverter.convert(new Yuv444PixelBlock(255, 0, 0)));
    }

    @Test
    public void testSomeColorConvert() throws Exception {
        // Accurate values in RGB: r = 66,05507; g = -11,85755; b = 177,27981;
        assertEquals(new Rgb888PixelBlock(66, 0, 177),
                Yuv444ToRgb888PixelBlockConverter.convert(new Yuv444PixelBlock(33, 71, 29)));
    }
}
