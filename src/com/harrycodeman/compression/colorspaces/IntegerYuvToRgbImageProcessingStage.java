package com.harrycodeman.compression.colorspaces;

public class IntegerYuvToRgbImageProcessingStage implements IImageProcessingStage {
    @Override
    public Image executeFor(Image image) {
        for (ThreeComponentPixelBlock block : image) {
            int y = block.getFirstAsPositiveInt();
            int u = block.getSecond();
            int v = block.getThird();
            block.setValues(
                    clamp((298*y +         409*v) >> 8),
                    clamp((298*y - 100*u - 208*v) >> 8),
                    clamp((298*y + 516*u        ) >> 8)
            );
        }
        return image;
    }

    private static int clamp(int value) {
        return value > 255 ? 255 : value < 0 ? 0 : value;
    }
}
