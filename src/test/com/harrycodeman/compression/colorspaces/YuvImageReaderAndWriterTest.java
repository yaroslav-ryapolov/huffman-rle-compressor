package test.com.harrycodeman.compression.colorspaces;

import com.harrycodeman.compression.colorspaces.*;
import org.junit.Test;

import java.io.File;

import static junit.framework.Assert.assertEquals;

public class YuvImageReaderAndWriterTest {
    private static final String FILE_PATH = "./data/test.yuv";

    @Test
    public void testSaveAndLoadImage() throws Exception {
        try {
            assertEquals(
                    new Image(2, 3,
                            new ThreeComponentPixelBlock(139, 84, 255),
                            new ThreeComponentPixelBlock(154, 84, 255),
                            new ThreeComponentPixelBlock(20, 255, 234),
                            new ThreeComponentPixelBlock(12, 255, 234),
                            new ThreeComponentPixelBlock(32, 212, 2),
                            new ThreeComponentPixelBlock(255, 212, 2)
                    ),
                    new ImageProcessingPipeline(
                            new YuvImageWriter(FILE_PATH),
                            new YuvImageReader(2, 3, FILE_PATH)
                    ).executeFor(
                            new Image(2, 3,
                                    new ThreeComponentPixelBlock(139, 84, 255),
                                    new ThreeComponentPixelBlock(154, 75, 79),
                                    new ThreeComponentPixelBlock(20, 255, 234),
                                    new ThreeComponentPixelBlock(12, 24, 33),
                                    new ThreeComponentPixelBlock(32, 212, 2),
                                    new ThreeComponentPixelBlock(255, 128, 128)
                            )
                    )
            );
        }
        finally {
            File f = new File(FILE_PATH);
            f.delete();
        }
    }
}
