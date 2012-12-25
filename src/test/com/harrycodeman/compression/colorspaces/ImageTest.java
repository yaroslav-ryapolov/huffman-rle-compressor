package test.com.harrycodeman.compression.colorspaces;

import com.harrycodeman.compression.colorspaces.Image;
import com.harrycodeman.compression.colorspaces.ImagePart8x8;
import com.harrycodeman.compression.colorspaces.ThreeComponentPixelBlock;
import org.junit.Before;
import org.junit.Test;

import static junit.framework.Assert.assertEquals;

public class ImageTest {
    private Image image = new Image(20, 20);

    @Before
    public void initialize() {
        for (int i = 0; i < 400; i++) {
            image.add(pb(i/20, i%20));
        }
    }

    @Test
    public void testGet8x8PartAtCenter() {
        assertEquals(
                new ImagePart8x8(
                        pb(0, 0), pb(0, 1), pb(0, 2), pb(0, 3), pb(0, 4), pb(0, 5), pb(0, 6), pb(0, 7),
                        pb(1, 0), pb(1, 1), pb(1, 2), pb(1, 3), pb(1, 4), pb(1, 5), pb(1, 6), pb(1, 7),
                        pb(2, 0), pb(2, 1), pb(2, 2), pb(2, 3), pb(2, 4), pb(2, 5), pb(2, 6), pb(2, 7),
                        pb(3, 0), pb(3, 1), pb(3, 2), pb(3, 3), pb(3, 4), pb(3, 5), pb(3, 6), pb(3, 7),
                        pb(4, 0), pb(4, 1), pb(4, 2), pb(4, 3), pb(4, 4), pb(4, 5), pb(4, 6), pb(4, 7),
                        pb(5, 0), pb(5, 1), pb(5, 2), pb(5, 3), pb(5, 4), pb(5, 5), pb(5, 6), pb(5, 7),
                        pb(6, 0), pb(6, 1), pb(6, 2), pb(6, 3), pb(6, 4), pb(6, 5), pb(6, 6), pb(6, 7),
                        pb(7, 0), pb(7, 1), pb(7, 2), pb(7, 3), pb(7, 4), pb(7, 5), pb(7, 6), pb(7, 7)
                ),
                image.get8x8Part(0)
        );
    }

    private ThreeComponentPixelBlock pb(int i, int j) {
        return new ThreeComponentPixelBlock(i, j, 0);
    }

    @Test
    public void testGet8x8PartWithRightCropped() {
        assertEquals(
                new ImagePart8x8(
                        pb(0, 16), pb(0, 17), pb(0, 18), pb(0, 19), empty(), empty(), empty(), empty(),
                        pb(1, 16), pb(1, 17), pb(1, 18), pb(1, 19), empty(), empty(), empty(), empty(),
                        pb(2, 16), pb(2, 17), pb(2, 18), pb(2, 19), empty(), empty(), empty(), empty(),
                        pb(3, 16), pb(3, 17), pb(3, 18), pb(3, 19), empty(), empty(), empty(), empty(),
                        pb(4, 16), pb(4, 17), pb(4, 18), pb(4, 19), empty(), empty(), empty(), empty(),
                        pb(5, 16), pb(5, 17), pb(5, 18), pb(5, 19), empty(), empty(), empty(), empty(),
                        pb(6, 16), pb(6, 17), pb(6, 18), pb(6, 19), empty(), empty(), empty(), empty(),
                        pb(7, 16), pb(7, 17), pb(7, 18), pb(7, 19), empty(), empty(), empty(), empty()
                ),
                image.get8x8Part(2));
    }

    private ThreeComponentPixelBlock empty() {
        return new ThreeComponentPixelBlock(0, 0, 0);
    }

    @Test
    public void testGet8x8PartWithBottomCropped() {
        assertEquals(
                new ImagePart8x8(
                        pb(16, 0), pb(16, 1), pb(16, 2), pb(16, 3), pb(16, 4), pb(16, 5), pb(16, 6), pb(16, 7),
                        pb(17, 0), pb(17, 1), pb(17, 2), pb(17, 3), pb(17, 4), pb(17, 5), pb(17, 6), pb(17, 7),
                        pb(18, 0), pb(18, 1), pb(18, 2), pb(18, 3), pb(18, 4), pb(18, 5), pb(18, 6), pb(18, 7),
                        pb(19, 0), pb(19, 1), pb(19, 2), pb(19, 3), pb(19, 4), pb(19, 5), pb(19, 6), pb(19, 7),
                        empty(),   empty(),   empty(),   empty(),   empty(),   empty(),   empty(),   empty(),
                        empty(),   empty(),   empty(),   empty(),   empty(),   empty(),   empty(),   empty(),
                        empty(),   empty(),   empty(),   empty(),   empty(),   empty(),   empty(),   empty(),
                        empty(),   empty(),   empty(),   empty(),   empty(),   empty(),   empty(),   empty()
                ), image.get8x8Part(6));
    }

    @Test
    public void testGet8x8PartWithRightAndBottomCropped() {
        assertEquals(new ImagePart8x8(
                pb(16, 16), pb(16, 17), pb(16, 18), pb(16, 19), empty(), empty(), empty(), empty(),
                pb(17, 16), pb(17, 17), pb(17, 18), pb(17, 19), empty(), empty(), empty(), empty(),
                pb(18, 16), pb(18, 17), pb(18, 18), pb(18, 19), empty(), empty(), empty(), empty(),
                pb(19, 16), pb(19, 17), pb(19, 18), pb(19, 19), empty(), empty(), empty(), empty(),
                empty(),    empty(),    empty(),    empty(),    empty(), empty(), empty(), empty(),
                empty(),    empty(),    empty(),    empty(),    empty(), empty(), empty(), empty(),
                empty(),    empty(),    empty(),    empty(),    empty(), empty(), empty(), empty(),
                empty(),    empty(),    empty(),    empty(),    empty(), empty(), empty(), empty()
        ), image.get8x8Part(8));
    }
}
