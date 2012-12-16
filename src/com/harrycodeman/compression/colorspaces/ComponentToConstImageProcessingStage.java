package com.harrycodeman.compression.colorspaces;

public class ComponentToConstImageProcessingStage extends EachPixelBlockImageProcessingStage {
    private int index;
    private int replacement;

    public ComponentToConstImageProcessingStage(int componentIndex, int replacement) {
        index = componentIndex;
        this.replacement = replacement;
    }

    @Override
    protected ThreeComponentPixelBlock processPixelBlock(ThreeComponentPixelBlock value) throws Exception {
        switch (index) {
            case 0:
                return new ThreeComponentPixelBlock(
                        replacement,
                        value.getSecondAsPositiveInt(),
                        value.getThirdAsPositiveInt()
                );
            case 1:
                return new ThreeComponentPixelBlock(
                        value.getFirstAsPositiveInt(),
                        replacement,
                        value.getThirdAsPositiveInt()
                );
            case 2:
                return new ThreeComponentPixelBlock(
                        value.getFirstAsPositiveInt(),
                        value.getSecondAsPositiveInt(),
                        replacement
                );
            default:
                return value;
        }
    }
}
