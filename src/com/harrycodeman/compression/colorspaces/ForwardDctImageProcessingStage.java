package com.harrycodeman.compression.colorspaces;

import static com.harrycodeman.compression.colorspaces.DctMatrices.DCT_TABLE;
import static com.harrycodeman.compression.colorspaces.DctMatrices.T_DCT_TABLE;
import static com.harrycodeman.compression.colorspaces.DctMatrices.LUMINANCE_QT;
import static com.harrycodeman.compression.colorspaces.DctMatrices.CHROMINANCE_QR;

public class ForwardDctImageProcessingStage implements IImageProcessingStage {
    @Override
    public Image executeFor(Image image) throws Exception {
        for (ImagePart p : image.get8x8Parts()) {
            CoefficientsMatrixBase firstComponentMatrix = new CoefficientsMatrixForImagePart(p, 0);
            firstComponentMatrix.copyByElementFrom(
                    DCT_TABLE.multiply(firstComponentMatrix)
                            .multiply(T_DCT_TABLE)
                            .divideByElement(LUMINANCE_QT)
            );

            CoefficientsMatrixBase secondComponentMatrix = new CoefficientsMatrixForImagePart(p, 1);
            secondComponentMatrix.copyByElementFrom(
                    DCT_TABLE.multiply(secondComponentMatrix)
                            .multiply(T_DCT_TABLE)
                            .divideByElement(CHROMINANCE_QR)
            );

            CoefficientsMatrixBase thirdComponentMatrix = new CoefficientsMatrixForImagePart(p, 2);
            thirdComponentMatrix.copyByElementFrom(
                    DCT_TABLE.multiply(thirdComponentMatrix)
                            .multiply(T_DCT_TABLE)
                            .divideByElement(CHROMINANCE_QR)
            );
        }
        return image;
    }
}
