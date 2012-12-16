package com.harrycodeman.compression.colorspaces;

public abstract class EachPixelBlockImageProcessingStage implements IImageProcessingStage {
    protected static int clamp(int value) {
        return value > 255 ? 255 : value < 0 ? 0 : value;
    }

    @Override
    public Image executeFor(Image image) throws Exception {
        for (int i = 0; i < image.size(); i++) {
            image.set(i, processPixelBlock(image.get(i)));
        }
        return image;
    }

    protected abstract ThreeComponentPixelBlock processPixelBlock(ThreeComponentPixelBlock value) throws Exception;
}
