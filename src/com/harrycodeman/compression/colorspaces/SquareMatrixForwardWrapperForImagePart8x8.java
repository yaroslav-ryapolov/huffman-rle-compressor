package com.harrycodeman.compression.colorspaces;

import static java.lang.Math.round;

public class SquareMatrixForwardWrapperForImagePart8x8 extends SquareMatrixBase {
    private ImagePart8x8 imagePart8x8;
    private int componentIndex;

    public SquareMatrixForwardWrapperForImagePart8x8(ImagePart8x8 imagePart8x8, int componentIndex) {
        super(ImagePart8x8.WIDTH);
        this.imagePart8x8 = imagePart8x8;
        this.componentIndex = componentIndex;
    }

    @Override
    public double get(int x, int y) {
        switch (componentIndex) {
            case 0:
                return (double)imagePart8x8.get(x, y).getFirstAsPositiveInt() - 128;
            case 1:
                return (double)imagePart8x8.get(x, y).getSecondAsPositiveInt() - 128;
            case 2:
                return (double)imagePart8x8.get(x, y).getThirdAsPositiveInt() - 128;
            default:
                return 0.0;
        }
    }

    @Override
    public void set(int x, int y, double value) {
        ThreeComponentPixelBlock b = imagePart8x8.get(x, y);
        byte valueAsByte = (byte)round(value);
        switch (componentIndex) {
            case 0:
                imagePart8x8.set(x, y,
                        new ThreeComponentPixelBlock(
                                valueAsByte,
                                b.getSecond(),
                                b.getThird()
                        )
                );
                break;
            case 1:
                imagePart8x8.set(x, y,
                        new ThreeComponentPixelBlock(
                                b.getFirst(),
                                valueAsByte,
                                b.getThird()
                        )
                );
                break;
            case 2:
                imagePart8x8.set(x, y,
                        new ThreeComponentPixelBlock(
                                b.getFirst(),
                                b.getSecond(),
                                valueAsByte
                        )
                );
                break;
        }
    }
}
