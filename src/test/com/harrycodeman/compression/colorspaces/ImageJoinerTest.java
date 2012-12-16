package test.com.harrycodeman.compression.colorspaces;

import com.harrycodeman.compression.colorspaces.Image;
import com.harrycodeman.compression.colorspaces.ImageJoiner;
import com.harrycodeman.compression.colorspaces.ThreeComponentPixelBlock;
import org.junit.Test;

import static junit.framework.Assert.assertEquals;

public class ImageJoinerTest {
    @Test
    public void testHorizontalJoinEqualHeight() throws Exception {
        assertEquals(
                new Image(2, 2,
                        new ThreeComponentPixelBlock(1, 1, 0), new ThreeComponentPixelBlock(2, 1, 0),
                        new ThreeComponentPixelBlock(1, 2, 0), new ThreeComponentPixelBlock(2, 2, 0)
                ),
                new ImageJoiner().horizontalJoin(
                        new Image(1, 2,
                                new ThreeComponentPixelBlock(1, 1, 0),
                                new ThreeComponentPixelBlock(1, 2, 0)
                        ),
                        new Image(1, 2,
                                new ThreeComponentPixelBlock(2, 1, 0),
                                new ThreeComponentPixelBlock(2, 2, 0)
                        )
                )
        );
    }

    @Test
    public void testHorizontalJoinLeftLowerThanRight() throws Exception {
        assertEquals(
                new Image(2, 3,
                        new ThreeComponentPixelBlock(255, 0, 0), new ThreeComponentPixelBlock(2, 1, 0),
                        new ThreeComponentPixelBlock(1, 2, 0), new ThreeComponentPixelBlock(2, 2, 0),
                        new ThreeComponentPixelBlock(1, 3, 0), new ThreeComponentPixelBlock(2, 3, 0)
                ),
                new ImageJoiner().horizontalJoin(
                        new Image(1, 2,
                                new ThreeComponentPixelBlock(1, 2, 0),
                                new ThreeComponentPixelBlock(1, 3, 0)
                        ),
                        new Image(1, 3,
                                new ThreeComponentPixelBlock(2, 1, 0),
                                new ThreeComponentPixelBlock(2, 2, 0),
                                new ThreeComponentPixelBlock(2, 3, 0)
                        )
                )
        );
    }

    @Test
    public void testHorizontalJoinLeftHigherThanRight() throws Exception {
        assertEquals(
                new Image(2, 3,
                        new ThreeComponentPixelBlock(1, 1, 0), new ThreeComponentPixelBlock(255, 0, 0),
                        new ThreeComponentPixelBlock(1, 2, 0), new ThreeComponentPixelBlock(2, 2, 0),
                        new ThreeComponentPixelBlock(1, 3, 0), new ThreeComponentPixelBlock(2, 3, 0)
                ),
                new ImageJoiner().horizontalJoin(
                        new Image(1, 3,
                                new ThreeComponentPixelBlock(1, 1, 0),
                                new ThreeComponentPixelBlock(1, 2, 0),
                                new ThreeComponentPixelBlock(1, 3, 0)
                        ),
                        new Image(1, 2,
                                new ThreeComponentPixelBlock(2, 2, 0),
                                new ThreeComponentPixelBlock(2, 3, 0)
                        )
                )
        );
    }
}
