package test.com.harrycodeman.compression.colorspaces;

import com.harrycodeman.compression.colorspaces.BuildHistogramImageProcessingStage;
import com.harrycodeman.compression.colorspaces.Image;
import com.harrycodeman.compression.colorspaces.ThreeComponentPixelBlock;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import static junit.framework.Assert.assertEquals;

public class BuildHistogramImageProcessingStageTest {

    @Test
    public void testExecuteFor() throws Exception {
        assertEquals(
                new Image(256, 3, getExpectedImagePixelBlocks()),
                new BuildHistogramImageProcessingStage().executeFor(
                        new Image(2, 3,
                                new ThreeComponentPixelBlock(0, 0, 0),
                                new ThreeComponentPixelBlock(254, 0, 0),
                                new ThreeComponentPixelBlock(0, 0, 0),
                                new ThreeComponentPixelBlock(0, 0, 0),
                                new ThreeComponentPixelBlock(254, 0, 0),
                                new ThreeComponentPixelBlock(254, 0, 0)
                        )
                )
        );
    }

    private Collection<ThreeComponentPixelBlock> getExpectedImagePixelBlocks() {
        List<ThreeComponentPixelBlock> pixelBlocks = new ArrayList<ThreeComponentPixelBlock>();
        for (int i = 0; i < 768; i++) {
            pixelBlocks.add(BuildHistogramImageProcessingStage.BACK_COLOR);
        }
        // set image points
//        pixelBlocks.set(0, new ThreeComponentPixelBlock(0, 0, 0));
//        pixelBlocks.set(1, new ThreeComponentPixelBlock(254, 0, 0));
//        pixelBlocks.set(258, new ThreeComponentPixelBlock(0, 0, 0));
//        pixelBlocks.set(259, new ThreeComponentPixelBlock(0, 0, 0));
//        pixelBlocks.set(516, new ThreeComponentPixelBlock(254, 0, 0));
//        pixelBlocks.set(517, new ThreeComponentPixelBlock(254, 0, 0));
        // one points for first = 0
        pixelBlocks.set(0, BuildHistogramImageProcessingStage.FORE_COLOR);
        pixelBlocks.set(256, BuildHistogramImageProcessingStage.FORE_COLOR);
        pixelBlocks.set(512, BuildHistogramImageProcessingStage.FORE_COLOR);
        // one point for first = 2
        pixelBlocks.set(254, BuildHistogramImageProcessingStage.FORE_COLOR);
        pixelBlocks.set(510, BuildHistogramImageProcessingStage.FORE_COLOR);
        pixelBlocks.set(766, BuildHistogramImageProcessingStage.FORE_COLOR);
        return pixelBlocks;
    }
}
