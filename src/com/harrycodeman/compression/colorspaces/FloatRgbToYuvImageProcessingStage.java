package com.harrycodeman.compression.colorspaces;

public class FloatRgbToYuvImageProcessingStage extends EachPixelBlockImageProcessingStage {
    @Override
    protected ThreeComponentPixelBlock processPixelBlock(ThreeComponentPixelBlock value) {
        int r = value.getFirstAsPositiveInt();
        int g = value.getSecondAsPositiveInt();
        int b = value.getThirdAsPositiveInt();
        return new ThreeComponentPixelBlock(
                (byte)(0.299*r + 0.587*g + 0.114*b),
                (byte)(-0.14713*r - 0.28886*g + 0.436*b),
                (byte)(0.615*r - 0.51499*g - 0.10001*b)
        );
    }
}
