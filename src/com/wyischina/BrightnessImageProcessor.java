package com.wyischina;


/**
 * An image processor that transform the brightness of an image
 */
public class BrightnessImageProcessor implements ImageProcessor {
    @Override
    public void process(Image input, Image output, float parameter) {
        for (int x = 0; x < input.getWidth(); x++) {
            for (int y = 0; y < input.getHeight(); y++) {
                output.setRed(x, y, rescaleColorValue(input.getRed(x, y), parameter));
                output.setGreen(x, y, rescaleColorValue(input.getGreen(x, y), parameter));
                output.setBlue(x, y, rescaleColorValue(input.getBlue(x, y), parameter));
            }
        }
    }

    /**
     * Rescale a value.
     *
     * @param value     color value (0-255) to be rescaled based on the parameter.
     * @param parameter a float between -1 and 1. 0 means no change to value
     * @return float the rescaled color value. 1 means take the value all the way to 255, -1 means take the value to 0, 0 means no change.
     */
    private int rescaleColorValue(int value, float parameter) {
        if (parameter > 0) {
            int range = 255 - value;
            return value + (int) (range * parameter);
        } else if (parameter < 0) {
            return value - (int) (value * Math.abs(parameter));
        } else {
            return value;
        }
    }

    @Override
    public String getName() {
        return "Brightness";
    }
}
