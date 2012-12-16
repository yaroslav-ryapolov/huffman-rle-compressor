package test.com.harrycodeman.compression.colorspaces;

import com.harrycodeman.compression.colorspaces.BuildHistogramImageProcessingStage;
import com.harrycodeman.compression.colorspaces.Image;
import com.harrycodeman.compression.colorspaces.ThreeComponentPixelBlock;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import static java.util.Collections.nCopies;
import static junit.framework.Assert.assertEquals;

public class BuildHistogramImageProcessingStageTest {
    // TODO: refactoring needs
    @Test
    public void testExecuteFor() throws Exception {
        assertEquals(
                new Image(256, 100, getBlocksForExecuteFor()),
                new BuildHistogramImageProcessingStage(0).executeFor(
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

    private Collection<ThreeComponentPixelBlock> getBlocksForExecuteFor() {
        List<ThreeComponentPixelBlock> result = new ArrayList<ThreeComponentPixelBlock>(
                nCopies(25600, BuildHistogramImageProcessingStage.BACK_COLOR)
        );
        for (int i = 0; i < 100; i++) {
            result.set(i*256, BuildHistogramImageProcessingStage.FORE_COLOR);
            result.set(i*256 + 254, BuildHistogramImageProcessingStage.FORE_COLOR);
        }
        return result;
    }

    @Test
    public void testForPixelsInterdependency() throws Exception {
        Image histogram = new BuildHistogramImageProcessingStage(0).executeFor(
                new Image(3, 1,
                        new ThreeComponentPixelBlock(0, 0, 0),
                        new ThreeComponentPixelBlock(0, 0, 0),
                        new ThreeComponentPixelBlock(0, 0, 0)
                )
        );
        for (int i = 0; i < histogram.size(); i++) {
            if (i%2 == 0) {
                histogram.set(i, new ThreeComponentPixelBlock(0, 0, 0));
            }
            else {
                histogram.set(i, new ThreeComponentPixelBlock(255, 255, 255));
            }
        }
        assertEquals(
                new Image(256, 100, getBlocksForPixelsInterdependency()),
                histogram
        );
    }

    private List<ThreeComponentPixelBlock> getBlocksForPixelsInterdependency() {
        List<ThreeComponentPixelBlock> result = new ArrayList<ThreeComponentPixelBlock>(25600);
        for (int i = 0; i < 25600; i++) {
            if (i%2 == 0) {
                result.add(new ThreeComponentPixelBlock(0, 0, 0));
            }
            else {
                result.add(new ThreeComponentPixelBlock(255, 255, 255));
            }
        }
        return result;
    }

    @Test
    public void testShift() throws Exception {
        assertEquals(
                new Image(256, 100, getBlocksForShift()),
                new BuildHistogramImageProcessingStage(4).executeFor(
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

    private Collection<ThreeComponentPixelBlock> getBlocksForShift() {
        List<ThreeComponentPixelBlock> result = new ArrayList<ThreeComponentPixelBlock>(
                nCopies(25600, BuildHistogramImageProcessingStage.BACK_COLOR)
        );
        for (int i = 0; i < 100; i++) {
            result.set(i*256 + 4, BuildHistogramImageProcessingStage.FORE_COLOR);
            result.set(i*256 + 255, BuildHistogramImageProcessingStage.FORE_COLOR);
        }
        return result;

    }
}
