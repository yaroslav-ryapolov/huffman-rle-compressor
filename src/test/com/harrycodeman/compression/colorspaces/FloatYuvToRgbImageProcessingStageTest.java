package test.com.harrycodeman.compression.colorspaces;

import com.harrycodeman.compression.colorspaces.FloatYuvToRgbImageProcessingStage;
import com.harrycodeman.compression.colorspaces.Image;
import com.harrycodeman.compression.colorspaces.ThreeComponentPixelBlock;
import org.junit.Test;

import static junit.framework.Assert.assertEquals;

public class FloatYuvToRgbImageProcessingStageTest {
    @Test
    public void testExecuteFor() throws Exception {
        Image image = new Image(1, 3,
                new ThreeComponentPixelBlock(0, 0, 0),
                new ThreeComponentPixelBlock(255, 0, 0),
                new ThreeComponentPixelBlock(33, 71, 29)
        );
        FloatYuvToRgbImageProcessingStage yuvToRgb = new FloatYuvToRgbImageProcessingStage();
        yuvToRgb.executeFor(image);
        assertEquals(
                new Image(1, 3,
                        new ThreeComponentPixelBlock(0, 0, 0),
                        new ThreeComponentPixelBlock(255, 255, 255),
                        // Accurate values in RGB: r = 66,05507; g = -11,85755; b = 177,27981;
                        new ThreeComponentPixelBlock(66, 0, 177)
                ),
                image
        );
    }
}
