package com.wyischina;

import java.awt.*;
import java.awt.image.BufferedImage;

/**
 * Image implementaiton that delgates to a BufferedImage.
 */
public class BufferedImageDelegate implements Image {

    /**
     * Underlying image.
     */
    private BufferedImage image;

    /**
     * Create a BufferedImageDelegate.
     * @param image the underlying image.
     */
    public BufferedImageDelegate(BufferedImage image) {
        this.image = image;
    }

    @Override
    public int getWidth() {
        return this.image.getWidth();
    }

    @Override
    public int getHeight() {
        return this.image.getHeight();
    }

    @Override
    public int getRed(int x, int y) {
        return new Color(image.getRGB(x, y)).getRed();
    }

    @Override
    public int getGreen(int x, int y) {
        return new Color(image.getRGB(x, y)).getGreen();
    }

    @Override
    public int getBlue(int x, int y) {
        return new Color(image.getRGB(x, y)).getBlue();
    }

    @Override
    public void setRed(int x, int y, int value) {
        Color color = new Color(value, getGreen(x, y), getBlue(x, y));
        image.setRGB(x, y, color.getRGB());
    }

    @Override
    public void setGreen(int x, int y, int value) {
        Color color = new Color(getRed(x, y), value, getBlue(x, y));
        image.setRGB(x, y, color.getRGB());
    }

    @Override
    public void setBlue(int x, int y, int value) {
        Color color = new Color(getRed(x, y), getGreen(x, y), value);
        image.setRGB(x, y, color.getRGB());
    }
}
