package com.wyischina;

import java.awt.*;

public class RotateImageProcessor implements ImageProcessor {
    @Override
    public void process(Image input, Image output, float parameter) {
        double angle = Math.toRadians(parameter * 360);
        double inverseAngle = -1 * angle;
        int centerX = input.getWidth() / 2;
        int centerY = input.getHeight() / 2;
        /**
         * Interesting algorithm. One can apply the rotation transformation in the forward direction, meaning given the input image (x,y),
         * find (x',y') after the rotation. This works theoretically but since we are dealing with integer calculation, due to rounding some
         * pixels in the output image may never be iterated over and set a color, therefore leaving strange artefacts on the output image.
         *
         * A better way is to deal with the rotation process in return. For each pixels on the output image (x', y'), find the original pixel
         * (x, y) before rotation. If we are rotating the input image 30 degrees, we are essentially rotating the output image -30 degrees to get
         * back the original image. This way we can guarantee finding a pixel in the original image, therefore setting a color if the pixel is
         * within bound of the image.
         */
        for (int x = 0; x < input.getWidth(); x++) {
            for (int y = 0; y < input.getHeight(); y++) {
                int previousX = (int) Math.round((x - centerX) * Math.cos(inverseAngle) - (y - centerY) * Math.sin(inverseAngle)) + centerX;
                int previousY = (int) Math.round((x - centerX) * Math.sin(inverseAngle) + (y - centerY) * Math.cos(inverseAngle)) + centerY;
                if (previousX >= 0 && previousX < input.getWidth() && previousY >= 0 && previousY < input.getHeight()) {
                    // previous x and y are within bound
                    output.setRed(x, y, input.getRed(previousX, previousY));
                    output.setGreen(x, y,  input.getGreen(previousX, previousY));
                    output.setBlue(x, y,  input.getBlue(previousX, previousY));
                }
            }
        }
    }


    @Override
    public String getName() {
        return "Rotate";
    }
}
