package com.harrycodeman.compression.colorspaces;

import static com.harrycodeman.compression.colorspaces.DctMatrices.forwardDct;

public class ForwardDctImageProcessingStage implements IImageProcessingStage {
    private final SquareMatrixBase quantizationTable;

    public ForwardDctImageProcessingStage(SquareMatrixBase quantizationTable) {
        this.quantizationTable = quantizationTable;
    }

    @Override
    public Image executeFor(Image image) throws Exception {
        for (ImagePart8x8 p : image.get8x8Parts()) {
            SquareMatrixBase firstComponentMatrix = new SquareMatrixForwardWrapperForImagePart8x8(p, 0);
            firstComponentMatrix.copyByElementFrom(
                    forwardDct(firstComponentMatrix).divideByElement(quantizationTable)
            );

            SquareMatrixBase secondComponentMatrix = new SquareMatrixForwardWrapperForImagePart8x8(p, 1);
            secondComponentMatrix.copyByElementFrom(
                    forwardDct(firstComponentMatrix).divideByElement(quantizationTable)
            );

            SquareMatrixBase thirdComponentMatrix = new SquareMatrixForwardWrapperForImagePart8x8(p, 2);
            thirdComponentMatrix.copyByElementFrom(
                    forwardDct(thirdComponentMatrix).divideByElement(quantizationTable)
            );
        }
        return image;
    }
}
