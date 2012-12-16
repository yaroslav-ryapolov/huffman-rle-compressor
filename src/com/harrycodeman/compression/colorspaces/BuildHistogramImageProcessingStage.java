package com.harrycodeman.compression.colorspaces;

import java.util.ArrayList;
import java.util.List;

import static java.util.Collections.nCopies;

public class BuildHistogramImageProcessingStage implements IImageProcessingStage {
    private static final int WIDTH = 257; // with white left column
    private static final int HEIGHT = 100;
    private static final int SIZE = WIDTH*HEIGHT;

    public static final ThreeComponentPixelBlock BACK_COLOR = new ThreeComponentPixelBlock(255, 0, 0);
    public static final ThreeComponentPixelBlock FORE_COLOR = new ThreeComponentPixelBlock(0, 0, 0);

    // TODO: do this class enable to use many times for different images
    private List<Integer> valuesCounter;
    private List<ThreeComponentPixelBlock> pixelBlocks;
    private double step;

    public BuildHistogramImageProcessingStage() {
        valuesCounter = new ArrayList<Integer>(nCopies(WIDTH, 0));
        pixelBlocks = new ArrayList<ThreeComponentPixelBlock>(nCopies(SIZE, BACK_COLOR));
    }

    @Override
    public Image executeFor(Image image) throws Exception {
        countValues(image);
        buildHistogram(image);
        return new ImageJoiner().horizontalJoinWithLeftImageModifying(image, new Image(WIDTH, HEIGHT, pixelBlocks));
    }

    private void countValues(Image image) {
        for (ThreeComponentPixelBlock b : image) {
            valuesCounter.set(
                    b.getFirstAsPositiveInt(),
                    valuesCounter.get(b.getFirstAsPositiveInt()) + 1
            );
        }
    }

    private void buildHistogram(Image image) {
        step = 6400.0/image.size();
        int columnNumber = 0;
        for (int c : valuesCounter) {
            buildColumn(columnNumber + 1, c);
            columnNumber++;
        }
    }

    private void buildColumn(int columnNumber, int value) {
        int height = (int)(step*value);
        height = height > 100 ? 100 : height;
        for (int j = 0; j < height; j++) {
            pixelBlocks.set((HEIGHT - j - 1)*WIDTH + columnNumber, FORE_COLOR);
        }
    }
}
