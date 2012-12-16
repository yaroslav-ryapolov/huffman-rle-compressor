package com.harrycodeman.compression.colorspaces;

public class IntegerRgbToYuvImageProcessingStage extends EachPixelBlockImageProcessingStage {
    @Override
    protected ThreeComponentPixelBlock processPixelBlock(ThreeComponentPixelBlock value) {
        int r = value.getFirstAsPositiveInt();
        int g = value.getSecondAsPositiveInt();
        int b = value.getThirdAsPositiveInt();
        return new ThreeComponentPixelBlock(
                (66*r + 129*g + 25*b) >> 8,
                (-37*r - 74*g + 112*b) >> 8,
                (112*r - 94*g - 18*b) >> 8
        );
    }
}
