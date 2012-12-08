package test.com.harrycodeman.compression.colorspaces;

import com.harrycodeman.compression.colorspaces.FloatRgbToYuvImageProcessingStage;
import com.harrycodeman.compression.colorspaces.Image;
import com.harrycodeman.compression.colorspaces.ThreeComponentPixelBlock;
import org.junit.Test;

import static junit.framework.Assert.assertEquals;

public class FloatRgbToYuvImageProcessingStageTest {
    @Test
    public void testExecuteFor() throws Exception {
        Image image = new Image(1, 3,
                new ThreeComponentPixelBlock(255, 255, 255),
                new ThreeComponentPixelBlock(0, 0, 0),
                new ThreeComponentPixelBlock(240, 37, 142)
        );
        FloatRgbToYuvImageProcessingStage rgbToYuv = new FloatRgbToYuvImageProcessingStage();
        rgbToYuv.executeFor(image);
        assertEquals(
                new Image(1, 3,
                        new ThreeComponentPixelBlock(255, 0, 0),
                        new ThreeComponentPixelBlock(0, 0, 0),
                        // Accurate values in YUV: y = 109,667; u = 15,91298; v = 114,34395;
                        new ThreeComponentPixelBlock(109, 15, 114)
                ),
                image
        );
    }
}
