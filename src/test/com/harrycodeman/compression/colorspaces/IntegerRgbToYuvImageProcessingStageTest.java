package test.com.harrycodeman.compression.colorspaces;

import com.harrycodeman.compression.colorspaces.Image;
import com.harrycodeman.compression.colorspaces.IntegerRgbToYuvImageProcessingStage;
import com.harrycodeman.compression.colorspaces.ThreeComponentPixelBlock;
import org.junit.Test;

import static junit.framework.Assert.assertEquals;

public class IntegerRgbToYuvImageProcessingStageTest {
    @Test
    public void testExecuteFor() {
        Image image = new Image(1, 3,
                new ThreeComponentPixelBlock(255, 255, 255),
                new ThreeComponentPixelBlock(0, 0, 0),
                new ThreeComponentPixelBlock(240, 37, 142)
        );
        IntegerRgbToYuvImageProcessingStage rgbToYuv = new IntegerRgbToYuvImageProcessingStage();
        rgbToYuv.executeFor(image);
        assertEquals(
                new Image(1, 3,
                        new ThreeComponentPixelBlock(219, 0, 0),
                        new ThreeComponentPixelBlock(0, 0, 0),
                        new ThreeComponentPixelBlock(94, 16, 81)
                ),
                image
        );
    }
}
