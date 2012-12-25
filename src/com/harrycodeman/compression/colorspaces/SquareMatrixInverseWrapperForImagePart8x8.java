package com.harrycodeman.compression.colorspaces;

import static java.lang.Math.round;

public class SquareMatrixInverseWrapperForImagePart8x8 extends SquareMatrixBase {
    private ImagePart8x8 imagePart8x8;
    private int componentIndex;

    public SquareMatrixInverseWrapperForImagePart8x8(ImagePart8x8 imagePart8x8, int componentIndex) {
        super(ImagePart8x8.WIDTH);
        this.imagePart8x8 = imagePart8x8;
        this.componentIndex = componentIndex;
    }

    @Override
    public double get(int x, int y) {
        switch (componentIndex) {
            case 0:
                return (double)imagePart8x8.get(x, y).getFirst();
            case 1:
                return (double)imagePart8x8.get(x, y).getSecond();
            case 2:
                return (double)imagePart8x8.get(x, y).getThird();
            default:
                return 0.0;
        }
    }

    @Override
    public void set(int x, int y, double value) {
        ThreeComponentPixelBlock b = imagePart8x8.get(x, y);
        int valueAsPositiveInt = (int)round(value) + 128;
        valueAsPositiveInt = valueAsPositiveInt > 255 ? 255 : valueAsPositiveInt < 0 ? 0 : valueAsPositiveInt;
        switch (componentIndex) {
            case 0:
                imagePart8x8.set(x, y,
                        new ThreeComponentPixelBlock(
                                valueAsPositiveInt,
                                b.getSecondAsPositiveInt(),
                                b.getThirdAsPositiveInt()
                        )
                );
                break;
            case 1:
                imagePart8x8.set(x, y,
                        new ThreeComponentPixelBlock(
                                b.getFirstAsPositiveInt(),
                                valueAsPositiveInt,
                                b.getThirdAsPositiveInt()
                        )
                );
                break;
            case 2:
                imagePart8x8.set(x, y,
                        new ThreeComponentPixelBlock(
                                b.getFirstAsPositiveInt(),
                                b.getSecondAsPositiveInt(),
                                valueAsPositiveInt
                        )
                );
                break;
        }
    }
}
