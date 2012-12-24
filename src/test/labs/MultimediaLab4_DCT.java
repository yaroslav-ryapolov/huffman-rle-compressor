package test.labs;

import com.harrycodeman.compression.colorspaces.*;
import org.junit.Test;

public class MultimediaLab4_DCT {
    @Test
    public void runForAllImages() throws Exception {
        runForImage("./data/lenna.pnm", "./data/multimedia/lenna");
        runForImage("./data/black-white.ppm", "./data/multimedia/black-white");
        runForImage("./data/house.ppm", "./data/multimedia/house");
        runForImage("./data/mountains.ppm", "./data/multimedia/mountains");
    }

    public void runForImage(String sourceFileName, String saveFileName) throws Exception {
        new ImageProcessingPipeline(
                new PpmImageReader(sourceFileName),
                new FloatRgbToYuvImageProcessingStage(),
                new ForwardDctImageProcessingStage(),
                new InverseDctImageProcessingStage(),
                new FloatYuvToRgbImageProcessingStage(),
                new PpmImageWriter(saveFileName + ".dctQuantizeAndBack.pnm")
        ).executeFromReader();
    }
}
