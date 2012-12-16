package com.harrycodeman.compression.colorspaces;

public class IntegerYuvToRgbImageProcessingStage extends EachPixelBlockImageProcessingStage {
    @Override
    protected ThreeComponentPixelBlock processPixelBlock(ThreeComponentPixelBlock value) {
        int y = value.getFirstAsPositiveInt();
        int u = value.getSecond();
        int v = value.getThird();
        return new ThreeComponentPixelBlock(
                clamp((298*y +         409*v) >> 8),
                clamp((298*y - 100*u - 208*v) >> 8),
                clamp((298*y + 516*u        ) >> 8)
        );
    }
}
