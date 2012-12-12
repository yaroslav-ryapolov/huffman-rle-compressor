package com.harrycodeman.compression.colorspaces;

import java.util.ArrayList;
import java.util.List;

import static java.util.Collections.max;
import static java.util.Collections.nCopies;

public class BuildHistogramImageProcessingStage implements IImageProcessingStage {
    private static final int WIDTH = 256;
    private static final int HEIGHT = 100;
    private static final int SIZE = WIDTH*HEIGHT;

    private static ThreeComponentPixelBlock getBackColor() {
        // TODO: implement in processing stage correct processing of the same objects!!!
        return new ThreeComponentPixelBlock(255, 0, 0);
    }
    private static ThreeComponentPixelBlock getForeColor(){
        // TODO: implement in processing stage correct processing of the same objects!!!
        return new ThreeComponentPixelBlock(0, 0, 0);
    }

    // TODO: do this class enable to use many times for different images
    private List<Integer> valuesCounter;
    private List<ThreeComponentPixelBlock> pixelBlocks;

    public BuildHistogramImageProcessingStage() {
        valuesCounter = new ArrayList<Integer>(
                nCopies(WIDTH, 0)
        );
        initializeHistogramBackground();
    }

    private void initializeHistogramBackground() {
        pixelBlocks = new ArrayList<ThreeComponentPixelBlock>();
        for (int i = 0; i < SIZE; i++) {
            pixelBlocks.add(getBackColor());
        }
    }

    @Override
    public Image executeFor(Image image) throws Exception {
        countValues(image);
        buildHistogram();
        // TODO: use horizontalJoinWith
        // return image.horizontalJoinWith(new Image(WIDTH, HEIGHT, pixelBlocks));
        return new Image(WIDTH, HEIGHT, pixelBlocks);
    }

    private void buildHistogram() {
        int maxCount = max(valuesCounter);
        double step = 100.0/maxCount;
        int i = 0;
        for (int c : valuesCounter) {
            int height = (int)(step*c);
            for (int j = 0; j < height; j++) {
                pixelBlocks.set((HEIGHT - j - 1)*WIDTH + i, getForeColor());
            }
            i++;
        }
    }

    private void countValues(Image image) {
        for (ThreeComponentPixelBlock b : image) {
            valuesCounter.set(
                    b.getFirstAsPositiveInt(),
                    valuesCounter.get(b.getFirstAsPositiveInt()) + 1
            );
        }
    }
}
