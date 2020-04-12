package com.wyischina;

public interface ImageProcessor {

    /**
     * Process the pixels of an input image and write pixels to the output image. Optionally the processor
     * can respond to the parameter value and produce different variation of the processor.
     * @param input image
     * @param output image
     * @param parameter entered by the user. The value of the parameter is between -1 and 1 inclusive. The default is 0.
     */
    void process(Image input, Image output, float parameter);

    /**
     * Returns the human readable name of the processor.
     * @return String name of this processor.
     */
    String getName();

}
