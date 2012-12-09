package com.harrycodeman.compression.colorspaces;

public interface IImageProcessingStage {
    public Image executeFor(Image image) throws Exception;
}
