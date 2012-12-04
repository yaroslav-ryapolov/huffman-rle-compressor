package test.com.harrycodeman.compression.colorspaces;

import com.harrycodeman.compression.colorspaces.Rgb888PixelBlock;
import com.harrycodeman.compression.colorspaces.Rgb888ToYuv444PixelBlockConverter;
import com.harrycodeman.compression.colorspaces.Yuv444PixelBlock;
import org.junit.Test;

import static junit.framework.Assert.assertEquals;

public class Rgb888ToYuv444PixelBlockConverterTest {
    @Test
    public void testWhiteConvert() throws Exception {
        assertEquals(new Yuv444PixelBlock(255, 0, 0),
                Rgb888ToYuv444PixelBlockConverter.convert(new Rgb888PixelBlock(255, 255, 255)));
    }

    @Test
    public void testBlackConvert() {
        assertEquals(new Yuv444PixelBlock(0, 0, 0),
                Rgb888ToYuv444PixelBlockConverter.convert(new Rgb888PixelBlock(0, 0, 0)));
    }

    @Test
    public void testSomeColorConvert() {
        // Accurate values in YUV: y = 109,667; u = 15,91298; v = 114,34395;
        assertEquals(new Yuv444PixelBlock(109, 15, 114),
                Rgb888ToYuv444PixelBlockConverter.convert(new Rgb888PixelBlock(240, 37, 142)));
    }
}
