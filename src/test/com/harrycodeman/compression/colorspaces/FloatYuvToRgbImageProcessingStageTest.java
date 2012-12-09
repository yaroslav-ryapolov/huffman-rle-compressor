package test.com.harrycodeman.compression.colorspaces;

import com.harrycodeman.compression.colorspaces.FloatYuvToRgbImageProcessingStage;
import com.harrycodeman.compression.colorspaces.Image;
import com.harrycodeman.compression.colorspaces.ThreeComponentPixelBlock;
import org.junit.Test;

import static junit.framework.Assert.assertEquals;

public class FloatYuvToRgbImageProcessingStageTest {
    @Test
    public void testExecuteFor() throws Exception {
        FloatYuvToRgbImageProcessingStage yuvToRgb = new FloatYuvToRgbImageProcessingStage();
        assertEquals(
                new Image(1, 3,
                        new ThreeComponentPixelBlock(0, 0, 0),
                        new ThreeComponentPixelBlock(255, 255, 255),
                        // Accurate values: r = -0,05507 (0 for [0, 255]); g = 21,81725; b = 177,27981;
                        new ThreeComponentPixelBlock(0, 21, 177)
                ),
                yuvToRgb.executeFor(
                        new Image(1, 3,
                                new ThreeComponentPixelBlock(0, 0, 0),
                                new ThreeComponentPixelBlock(255, 0, 0),
                                new ThreeComponentPixelBlock(33, 71, -29)
                        )
                )
        );
    }
}
