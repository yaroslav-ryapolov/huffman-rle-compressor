package com.harrycodeman.compression.colorspaces;

public class FloatYuvToRgbImageProcessingStage implements IImageProcessingStage {
    @Override
    public Image executeFor(Image image) {
        for (ThreeComponentPixelBlock block : image) {
            int y = block.getFirstAsPositiveInt();
            int u = block.getSecond();
            int v = block.getThird();
            block.setValues(
                    clamp(y +             1.13983*v),
                    clamp(y - 0.39465*u - 0.58060*v),
                    clamp(y + 2.03211*u)
            );
        }
        return image;
    }

    private static int clamp(double value) {
        int intValue = (int)value;
        return intValue > 255 ? 255 : intValue < 0 ? 0 : intValue;
    }
}
