package com.harrycodeman.compression.colorspaces;

import static com.harrycodeman.compression.colorspaces.DctMatrices.*;

public class ForwardDctImageProcessingStage implements IImageProcessingStage {
    @Override
    public Image executeFor(Image image) throws Exception {
        for (ImagePart p : image.get8x8Parts()) {
            CoefficientsMatrixBase firstComponentMatrix = new CoefficientsMatrixWrapperForImagePart(p, 0);
            firstComponentMatrix.copyByElementFrom(
                    DCT_TABLE.multiply(firstComponentMatrix)
                            .multiply(T_DCT_TABLE)
                            .divideByElement(LUMINANCE_QT)
            );

            CoefficientsMatrixBase secondComponentMatrix = new CoefficientsMatrixWrapperForImagePart(p, 1);
            secondComponentMatrix.copyByElementFrom(
                    DCT_TABLE.multiply(secondComponentMatrix)
                            .multiply(T_DCT_TABLE)
                            .divideByElement(CHROMINANCE_QR)
            );

            CoefficientsMatrixBase thirdComponentMatrix = new CoefficientsMatrixWrapperForImagePart(p, 2);
            thirdComponentMatrix.copyByElementFrom(
                    DCT_TABLE.multiply(thirdComponentMatrix)
                            .multiply(T_DCT_TABLE)
                            .divideByElement(CHROMINANCE_QR)
            );
        }
        return image;
    }
}
