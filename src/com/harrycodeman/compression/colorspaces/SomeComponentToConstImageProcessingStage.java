package com.harrycodeman.compression.colorspaces;

public class SomeComponentToConstImageProcessingStage implements IImageProcessingStage {
    private int index;
    private int replacement;

    public SomeComponentToConstImageProcessingStage(int componentIndex, int replacement) {
        index = componentIndex;
        this.replacement = replacement;
    }

    @Override
    public void executeFor(Image image) throws Exception {
        for (ThreeComponentPixelBlock b : image) {
            replaceComponent(b);
        }
    }

    private void replaceComponent(ThreeComponentPixelBlock b) {
        switch (index) {
            case 0:
                b.setFirst(replacement);
                break;
            case 1:
                b.setSecond(replacement);
                break;
            case 2:
                b.setThird(replacement);
                break;
        }
    }
}
