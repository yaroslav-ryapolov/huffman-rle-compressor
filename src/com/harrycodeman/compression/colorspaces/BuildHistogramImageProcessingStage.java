package com.harrycodeman.compression.colorspaces;

import java.util.ArrayList;
import java.util.List;

import static java.util.Collections.max;
import static java.util.Collections.min;
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
    private final int shift;
    private List<Integer> valuesCounter;
    private List<ThreeComponentPixelBlock> pixelBlocks;
    private double step;

    public BuildHistogramImageProcessingStage(int shift) {
        this.shift = shift;
        initializeValuesCounter();
        initializeHistogramBackground();
    }

    private void initializeValuesCounter() {
        valuesCounter = new ArrayList<Integer>(
                nCopies(WIDTH, 0)
        );
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

    private void countValues(Image image) {
        for (ThreeComponentPixelBlock b : image) {
            int shiftedValue = getShiftedComponent(b.getFirstAsPositiveInt());
            valuesCounter.set(
                    shiftedValue,
                    valuesCounter.get(shiftedValue) + 1
            );
        }
    }

    private int getShiftedComponent(int value) {
        int shiftedValue = value + shift;
        if (shiftedValue > 255) {
            return 255;
        }
        if (shiftedValue < 0) {
            return 0;
        }
        return shiftedValue;
    }

    private void buildHistogram() {
        step = 100.0/max(valuesCounter);
        int columnNumber = 0;
        for (int c : valuesCounter) {
            buildColumn(columnNumber, c);
            columnNumber++;
        }
    }

    private void buildColumn(int columnNumber, int value) {
        int height = (int)(step*value);
        for (int j = 0; j < height; j++) {
            pixelBlocks.set((HEIGHT - j - 1)*WIDTH + columnNumber, getForeColor());
        }
    }
}
