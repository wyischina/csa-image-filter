package com.wyischina;

/**
 * Abstraction of an image.
 */
public interface Image {

    /**
     * Get number of pixels horizontally
     *
     * @return int number of pixels horizontally
     */
    int getWidth();

    /**
     * Get number of pixels vertically
     *
     * @return int number of pixels vertically
     */
    int getHeight();

    /**
     * Get the red value at the given pixel
     *
     * @param x coordinate of the pixel
     * @param y coordinate of the pixel
     * @return the red value
     */
    int getRed(int x, int y);


    /**
     * Get the green value at the given pixel
     *
     * @param x coordinate of the pixel
     * @param y coordinate of the pixel
     * @return the green value
     */
    int getGreen(int x, int y);

    /**
     * Get the blue value at the given pixel
     *
     * @param x coordinate of the pixel
     * @param y coordinate of the pixel
     * @return the blue value
     */
    int getBlue(int x, int y);

    /**
     * Set the red value at the given pixel
     *
     * @param x     coordinate of the pixel
     * @param y     coordinate of the pixel
     * @param value of the pixel
     */
    void setRed(int x, int y, int value);


    /**
     * Set the green value at the given pixel
     *
     * @param x     coordinate of the pixel
     * @param y     coordinate of the pixel
     * @param value of the pixel
     */
    void setGreen(int x, int y, int value);

    /**
     * Set the blue value at the given pixel
     *
     * @param x     coordinate of the pixel
     * @param y     coordinate of the pixel
     * @param value of the pixel
     */
    void setBlue(int x, int y, int value);
}
