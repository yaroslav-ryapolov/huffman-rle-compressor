package test.com.harrycodeman.compression.colorspaces;

import com.harrycodeman.compression.colorspaces.Image;
import com.harrycodeman.compression.colorspaces.IntegerYuvToRgbImageProcessingStage;
import com.harrycodeman.compression.colorspaces.ThreeComponentPixelBlock;
import org.junit.Test;

import static junit.framework.Assert.assertEquals;

public class IntegerYuvToRgbImageProcessingStageTest {
    @Test
    public void testExecuteFor() throws Exception {
        Image image = new Image(1, 3,
                new ThreeComponentPixelBlock(0, 0, 0),
                new ThreeComponentPixelBlock(255, 0, 0),
                new ThreeComponentPixelBlock(33, 71, -29)
        );
        IntegerYuvToRgbImageProcessingStage yuvToRgb = new IntegerYuvToRgbImageProcessingStage();
        yuvToRgb.executeFor(image);
        assertEquals(
                new Image(1, 3,
                        new ThreeComponentPixelBlock(0, 0, 0),
                        new ThreeComponentPixelBlock(255, 255, 255),
                        new ThreeComponentPixelBlock(0, 34, 182)
                ),
                image
        );
    }
}
