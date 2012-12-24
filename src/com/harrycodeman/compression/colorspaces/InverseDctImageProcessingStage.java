package com.harrycodeman.compression.colorspaces;

import static com.harrycodeman.compression.colorspaces.DctMatrices.*;

public class InverseDctImageProcessingStage implements IImageProcessingStage {
    @Override
    public Image executeFor(Image image) throws Exception {
        for (ImagePart p : image.get8x8Parts()) {
            CoefficientsMatrixBase firstComponentMatrix = new CoefficientsMatrixForImagePart(p, 0);
            firstComponentMatrix.copyByElementFrom(
                    T_DCT_TABLE.multiply(
                            firstComponentMatrix.multiplyByElement(LUMINANCE_QT)
                    ).multiply(DCT_TABLE)
            );

            CoefficientsMatrixBase secondComponentMatrix = new CoefficientsMatrixForImagePart(p, 1);
            secondComponentMatrix.copyByElementFrom(
                    T_DCT_TABLE.multiply(
                            secondComponentMatrix.multiplyByElement(CHROMINANCE_QR)
                    ).multiply(DCT_TABLE)

            );

            CoefficientsMatrixBase thirdComponentMatrix = new CoefficientsMatrixForImagePart(p, 2);
            thirdComponentMatrix.copyByElementFrom(
                    T_DCT_TABLE.multiply(
                            thirdComponentMatrix.multiplyByElement(CHROMINANCE_QR)
                    ).multiply(DCT_TABLE)
            );
        }
        return image;
    }
}
