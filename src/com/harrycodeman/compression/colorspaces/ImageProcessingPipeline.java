package com.harrycodeman.compression.colorspaces;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class ImageProcessingPipeline implements IImageProcessingStage {
    private List<IImageProcessingStage> stages = new ArrayList<IImageProcessingStage>();

    public ImageProcessingPipeline(IImageProcessingStage... stages) {
        Collections.addAll(this.stages, stages);
    }

    public void executeFromReader() throws Exception {
        executeFor(null);
    }

    @Override
    public Image executeFor(Image image) throws Exception {
        for (IImageProcessingStage s : stages) {
            image = s.executeFor(image);
        }
        return image;
    }
}
