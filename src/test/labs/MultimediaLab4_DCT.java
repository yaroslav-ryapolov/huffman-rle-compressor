package test.labs;

import com.harrycodeman.compression.colorspaces.*;
import org.junit.Test;

import static com.harrycodeman.compression.colorspaces.DctMatrices.*;

public class MultimediaLab4_DCT {
    @Test
    public void runForAllImages() throws Exception {
        runForImage("./data/black-white.ppm", "./data/multimedia/black-white.16", QUANTIZATION_TABLE_16);
        runForImage("./data/black-white.ppm", "./data/multimedia/black-white.jpeg", QUANTIZATION_TABLE_JPEG);
        runForImage("./data/black-white.ppm", "./data/multimedia/black-white.t_jpeg", QUANTIZATION_TABLE_T_JPEG);
        runForImage("./data/lenna.pnm", "./data/multimedia/lenna.16", QUANTIZATION_TABLE_16);
        runForImage("./data/lenna.pnm", "./data/multimedia/lenna.jpeg", QUANTIZATION_TABLE_JPEG);
        runForImage("./data/lenna.pnm", "./data/multimedia/lenna.t_jpeg", QUANTIZATION_TABLE_T_JPEG);
    }

    public void runForImage(String sourceFileName, String saveFileName, SquareMatrixBase quantizationTable)
            throws Exception {
        new ImageProcessingPipeline(
                new PpmImageReader(sourceFileName),
                new FloatRgbToYuvImageProcessingStage(),
                new ForwardDctImageProcessingStage(quantizationTable),
                new PpmImageWriter(saveFileName + ".forward.pnm"),
                new InverseDctImageProcessingStage(quantizationTable),
                new FloatYuvToRgbImageProcessingStage(),
                new PpmImageWriter(saveFileName + ".inverse.pnm")
        ).executeFromReader();
    }
}
