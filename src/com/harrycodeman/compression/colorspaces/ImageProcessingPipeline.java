package com.harrycodeman.compression.colorspaces;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class ImageProcessingPipeline implements IImageProcessingStage {
    private List<IImageProcessingStage> stages = new ArrayList<IImageProcessingStage>();

    public ImageProcessingPipeline(IImageProcessingStage... stages) {
        Collections.addAll(this.stages, stages);
    }

    @Override
    public void executeFor(Image image) throws Exception {
        for (IImageProcessingStage s : stages) {
            s.executeFor(image);
        }
    }
}
