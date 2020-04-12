package com.wyischina;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class ImageUtils {

    /**
     * Get the color value of the box surrounding the x,y coordinate.
     * @param input image
     * @param x coordinate
     * @param y coordinate
     * @param boxSize number of horizontal/vertical pixel distance from x, y.
     * @return List<Color> representing all pixels in the box.
     */
    public static java.util.List<Color> getBox(Image input, int x, int y, int boxSize) {
        List<Color> colors = new ArrayList<>();
        int xFrom = Math.max(0, x - boxSize);
        int yFrom = Math.max(0, y - boxSize);
        int xTo = Math.min(input.getWidth() - 1, x + boxSize);
        int yTo = Math.min(input.getHeight() - 1, y + boxSize);
        for (int col = xFrom; col <= xTo; col++) {
            for (int row = yFrom; row <= yTo; row++) {
                Color color = new Color(input.getRed(col, row), input.getGreen(col, row), input.getBlue(col, row));
                colors.add(color);
            }
        }
        return colors;
    }

    /**
     * Compute an average out of a list of colors.
     * @param pixels colors.
     * @return Color the average
     */
    public static Color averageColor(List<Color> pixels) {
        int red = pixels.stream().map(Color::getRed).reduce(0, Integer::sum) / pixels.size();
        int green = pixels.stream().map(Color::getGreen).reduce(0, Integer::sum) / pixels.size();
        int blue = pixels.stream().map(Color::getBlue).reduce(0, Integer::sum) / pixels.size();
        return new Color(red, green, blue);
    }


}
