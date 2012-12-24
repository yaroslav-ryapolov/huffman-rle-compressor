package com.harrycodeman.compression.colorspaces;

import static java.lang.Math.round;

public class CoefficientsMatrixWrapperForImagePart extends CoefficientsMatrixBase {
    private ImagePart imagePart;
    private int componentIndex;

    public CoefficientsMatrixWrapperForImagePart(ImagePart imagePart, int componentIndex) {
        this.imagePart = imagePart;
        this.componentIndex = componentIndex;
    }

    @Override
    public double get(int x, int y) {
        switch (componentIndex) {
            case 0:
                return imagePart.get(x, y).getFirst();
            case 1:
                return imagePart.get(x, y).getSecond();
            case 2:
                return imagePart.get(x, y).getThird();
            default:
                return 0.0;
        }
    }

    @Override
    public void set(int x, int y, double value) {
        ThreeComponentPixelBlock b = imagePart.get(x, y);
        byte valueAsByte = value > 127.0 ? 127 : value < -128.0 ? -128 : (byte)round(value);
        switch (componentIndex) {
            case 0:
                imagePart.set(x, y,
                        new ThreeComponentPixelBlock(
                                valueAsByte,
                                b.getSecond(),
                                b.getThird()
                        )
                );
                break;
            case 1:
                imagePart.set(x, y,
                        new ThreeComponentPixelBlock(
                                b.getFirst(),
                                valueAsByte,
                                b.getThird()
                        )
                );
                break;
            case 2:
                imagePart.set(x, y,
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
