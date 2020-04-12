package com.wyischina;


/**
 * An image processor that scales the image horizontally.
 *
 * One interesting aspect of this scaling method is that for each column in the original image, it calculates where the resulting column is
 * in the new image. However, there are columns between the starting column and the resulting column, so we need to fill the columns in between with
 * the same column pixels to achieve the stretch effect.
 */
public class HorizontalScaleImageProcessor implements ImageProcessor {
    @Override
    public void process(Image input, Image output, float parameter) {
        int halfWidth =input.getWidth() / 2;
        float scale = parameter + 1;
        int scaledWidth = (int) (input.getWidth() * scale);
        int lastX = 0;
        for (int x = 0; x < input.getWidth(); x++) {
            int newX = Math.max(0, (int) ((x * 1.0d / input.getWidth()) * scaledWidth)) ;
            for(int x2 = lastX; x2 <= Math.min(newX, input.getWidth() - 1); x2++) {
                for (int y = 0; y < input.getHeight(); y++) {
                    output.setRed(x2, y, input.getRed(x, y));
                    output.setGreen(x2, y, input.getGreen(x, y));
                    output.setBlue(x2, y, input.getBlue(x, y));
                }
            }
            lastX = newX;
        }
    }

    public float scaleFactor(float parameter, int x, int width) {
        int distanceToCenter = Math.abs(x - width / 2);
        return parameter * 10 * distanceToCenter;
    }

    @Override
    public String getName() {
        return "Scale horizontally";
    }
}
