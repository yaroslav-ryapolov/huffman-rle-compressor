package com.harrycodeman.compression.colorspaces;

public class FloatRgbToYuvImageProcessingStage implements IImageProcessingStage {
    @Override
    public void executeFor(Image image) {
        for (ThreeComponentPixelBlock block : image) {
            int r = block.getFirst();
            int g = block.getSecond();
            int b = block.getThird();
            block.setValues(
                    (int) (0.299 * r + 0.587 * g + 0.114 * b),
                    (int) (-0.14713 * r - 0.28886 * g + 0.436 * b),
                    (int) (0.615 * r - 0.51499 * g - 0.10001 * b)
            );
        }
    }
}
