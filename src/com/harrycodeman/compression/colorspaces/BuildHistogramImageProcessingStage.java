package com.harrycodeman.compression.colorspaces;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

public class BuildHistogramImageProcessingStage implements IImageProcessingStage {
    private static final int WIDTH = 256;
    public static final ThreeComponentPixelBlock BACK_COLOR = new ThreeComponentPixelBlock(255, 0, 0);
    public static final ThreeComponentPixelBlock FORE_COLOR = new ThreeComponentPixelBlock(0, 0, 0);

    private List<Integer> valueCounter = new ArrayList<Integer>(WIDTH);

    public BuildHistogramImageProcessingStage() {
        initializeValueCounter();
    }

    private void initializeValueCounter() {
        Collections.addAll(valueCounter, new Integer[WIDTH]);
        Collections.fill(valueCounter, 0);
    }

    @Override
    public Image executeFor(Image image) throws Exception {
        return new Image(image.getWidth() + WIDTH, image.getHeight(), getResultImagePixelBlocks(image));
    }

    public Collection<ThreeComponentPixelBlock> getResultImagePixelBlocks(Image image) {
        List<ThreeComponentPixelBlock> histogram = buildHistogram(image);
        return join(image, histogram);
    }

    private List<ThreeComponentPixelBlock> buildHistogram(Image image) {
        List<ThreeComponentPixelBlock> histogramBlocks = createHistogramBackground(image.getHeight());
        for (ThreeComponentPixelBlock b : image) {
            int value = b.getFirstAsPositiveInt();
            int rowNumber = incrementValueAndGetRowNumber(value, image);
            if (rowNumber >= 0) {
                histogramBlocks.set(WIDTH *rowNumber + value, FORE_COLOR);
            }
        }
        return histogramBlocks;
    }

    private static List<ThreeComponentPixelBlock> createHistogramBackground(int height) {
        List<ThreeComponentPixelBlock> pixelBlocks = new ArrayList<ThreeComponentPixelBlock>();
        for (int i = 0; i < WIDTH*height; i++) {
            pixelBlocks.add(BACK_COLOR);
        }
        return pixelBlocks;
    }

    private int incrementValueAndGetRowNumber(int value, Image image) {
        int count = valueCounter.get(value);
        valueCounter.set(value, count + 1);
        if (count%image.getWidth() > (count + 1)%image.getWidth()) {
            return image.getHeight() - count/image.getWidth() - 1;
        }
        return -1;
    }

    private Collection<ThreeComponentPixelBlock> join(Image image, List<ThreeComponentPixelBlock> histogram) {
        List<ThreeComponentPixelBlock> result = new ArrayList<ThreeComponentPixelBlock>(
                image.getWidth()*image.getHeight() + histogram.size()
        );
        for (int i = 0; i < image.getHeight(); i++) {
            result.addAll(image.getRowBlocks(i));
            result.addAll(histogram.subList(i*WIDTH, i*WIDTH + WIDTH));
        }
        return result;
    }
}
