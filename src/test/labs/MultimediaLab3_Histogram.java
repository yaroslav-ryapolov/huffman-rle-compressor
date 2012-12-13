package test.labs;

import com.harrycodeman.compression.colorspaces.*;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

public class MultimediaLab3_Histogram {
    @Test
    public void buildHistograms() throws Exception {
        buildHistogram("./data/lenna.pnm", "./data/multimedia/lenna");
        buildHistogram("./data/black-white.ppm", "./data/multimedia/black-white");
        buildHistogram("./data/house.ppm", "./data/multimedia/house");
        buildHistogram("./data/mountains.ppm", "./data/multimedia/mountains");
    }

    public void buildHistogram(String filePath, String savePath) throws Exception {
        for (int s = -256; s <= 256; s += 64) {
            new ImageProcessingPipeline(
                    new PpmImageReader(filePath),
                    new FloatRgbToYuvImageProcessingStage(),
                    new BuildHistogramImageProcessingStage(s),
                    new FloatYuvToRgbImageProcessingStage(),
                    new PpmImageWriter(savePath + ".histogram" + s + ".pnm")
            ).executeFor(null);
        }
    }
}
