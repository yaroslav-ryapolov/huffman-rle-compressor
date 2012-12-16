package com.harrycodeman.compression.colorspaces;

public class ShiftFirstComponentImageProcessingStage extends EachPixelBlockImageProcessingStage {
    private int shift;

    public ShiftFirstComponentImageProcessingStage(int shift) {
        this.shift = shift;
    }

    @Override
    protected ThreeComponentPixelBlock processPixelBlock(ThreeComponentPixelBlock value) throws Exception {
        return new ThreeComponentPixelBlock(
                getShiftedComponent(value.getFirstAsPositiveInt()),
                value.getSecondAsPositiveInt(),
                value.getThirdAsPositiveInt()
        );
    }

    private int getShiftedComponent(int value) {
        int shiftedValue = value + shift;
        return shiftedValue > 255 ? 255 : shiftedValue < 0 ? 0 : shiftedValue;
    }
}
