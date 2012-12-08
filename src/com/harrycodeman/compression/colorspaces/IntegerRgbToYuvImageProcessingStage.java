package com.harrycodeman.compression.colorspaces;

public class IntegerRgbToYuvImageProcessingStage implements IImageProcessingStage {
    @Override
    public void executeFor(Image image) {
        for (ThreeComponentPixelBlock block : image) {
            int r = block.getFirst();
            int g = block.getSecond();
            int b = block.getThird();
            block.setValues(
                    (66*r + 129*g + 25*b) >> 8,
                    (-37*r - 74*g + 112*b) >> 8,
                    (112*r - 94*g - 18*b) >> 8
            );
        }
    }
}
