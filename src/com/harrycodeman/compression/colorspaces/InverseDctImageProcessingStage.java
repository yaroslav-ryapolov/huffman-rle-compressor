package com.harrycodeman.compression.colorspaces;

import static com.harrycodeman.compression.colorspaces.DctMatrices.inverseDct;

public class InverseDctImageProcessingStage implements IImageProcessingStage {
    private final SquareMatrixBase quantizationTable;

    public InverseDctImageProcessingStage(SquareMatrixBase quantizationTable) {
        this.quantizationTable = quantizationTable;
    }

    @Override
    public Image executeFor(Image image) throws Exception {
        for (ImagePart8x8 p : image.get8x8Parts()) {
            SquareMatrixBase firstComponentMatrix = new SquareMatrixInverseWrapperForImagePart8x8(p, 0);
            firstComponentMatrix.copyByElementFrom(
                    inverseDct(firstComponentMatrix.multiplyByElement(quantizationTable))
            );

            SquareMatrixBase secondComponentMatrix = new SquareMatrixInverseWrapperForImagePart8x8(p, 1);
            secondComponentMatrix.copyByElementFrom(
                    inverseDct(secondComponentMatrix.multiplyByElement(quantizationTable))
            );

            SquareMatrixBase thirdComponentMatrix = new SquareMatrixInverseWrapperForImagePart8x8(p, 2);
            thirdComponentMatrix.copyByElementFrom(
                    inverseDct(thirdComponentMatrix.multiplyByElement(quantizationTable))
            );
        }
        return image;
    }
}
