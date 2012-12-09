package test.com.harrycodeman.compression.colorspaces;

import com.harrycodeman.compression.colorspaces.*;
import org.junit.Test;

import java.io.File;

import static junit.framework.Assert.assertEquals;

public class PpmImageReaderAndWriterTest {
    private static final String FILE_PATH = "./data/test.pnm";

    @Test
    public void testSaveAndLoadImage() throws Exception {
        try {
            assertEquals(
                    getImage(),
                    new ImageProcessingPipeline(
                            new PpmImageWriter(FILE_PATH),
                            new PpmImageReader(FILE_PATH)
                    ).executeFor(getImage())
            );
        }
        finally {
            File f = new File(FILE_PATH);
            f.delete();
        }
    }

    private Image getImage() {
        return new Image(2, 3,
                new ThreeComponentPixelBlock(226, 13, 133),
                new ThreeComponentPixelBlock(226, 17, 12),
                new ThreeComponentPixelBlock(223, 127, 13),
                new ThreeComponentPixelBlock(226, 117, 25),
                new ThreeComponentPixelBlock(226, 12, 125),
                new ThreeComponentPixelBlock(223, 112, 33)
        );
    }
}
