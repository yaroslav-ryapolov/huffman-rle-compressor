package com.harrycodeman.compression.colorspaces;

public class FloatRgbToYuvImageProcessingStage implements IImageProcessingStage {
    @Override
    public Image executeFor(Image image) {
        for (ThreeComponentPixelBlock block : image) {
            int r = block.getFirstAsPositiveInt();
            int g = block.getSecondAsPositiveInt();
            int b = block.getThirdAsPositiveInt();
            block.setValues(
                    (byte)(0.299*r + 0.587*g + 0.114*b),
                    (byte)(-0.14713*r - 0.28886*g + 0.436*b),
                    (byte)(0.615*r - 0.51499*g - 0.10001*b)
            );
        }
        return image;
    }
}
