package com.harrycodeman.compression.colorspaces;

public class FloatYuvToRgbImageProcessingStage extends EachPixelBlockImageProcessingStage {
    @Override
    protected ThreeComponentPixelBlock processPixelBlock(ThreeComponentPixelBlock value) {
        int y = value.getFirstAsPositiveInt();
        int u = value.getSecond();
        int v = value.getThird();
        return new ThreeComponentPixelBlock(
                clamp((int)(y +             1.13983*v)),
                clamp((int)(y - 0.39465*u - 0.58060*v)),
                clamp((int)(y + 2.03211*u))
        );
    }
}
