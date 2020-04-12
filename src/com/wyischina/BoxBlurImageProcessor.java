package com.wyischina;


import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

import static com.wyischina.ImageUtils.averageColor;
import static com.wyischina.ImageUtils.getBox;

/**
 * An image processor that transform the brightness of an image
 */
public class BoxBlurImageProcessor implements ImageProcessor {
    @Override
    public void process(Image input, Image output, float parameter) {
        int boxSize = (int) (parameter * 3);
        for (int x = 0; x < input.getWidth(); x++) {
            for (int y = 0; y < input.getHeight(); y++) {
                List<Color> box = getBox(input, x, y, boxSize);
                Color average = averageColor(box);
                output.setRed(x, y, average.getRed());
                output.setGreen(x, y, average.getGreen());
                output.setBlue(x, y, average.getBlue());
            }
        }
    }


    @Override
    public String getName() {
        return "Box blur";
    }
}
