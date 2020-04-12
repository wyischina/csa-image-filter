package com.wyischina;

public class HorizontalShiftImageProcessor implements ImageProcessor {
    @Override
    public void process(Image input, Image output, float parameter) {
        for (int x = 0; x < input.getWidth(); x++) {
            int newX = x + (int) (parameter * input.getWidth());
            for (int y = 0; y < input.getHeight(); y++) {
                if (newX < 0 || newX >= input.getWidth()) {
                    /** output.setRed(x, y, 0);
                    output.setGreen(x, y, 0);
                    output.setBlue(x, y, 0); */
                } else {
                    output.setRed(newX, y, input.getRed(x, y));
                    output.setGreen(newX, y, input.getGreen(x, y));
                    output.setBlue(newX, y, input.getBlue(x, y));
                }
            }
        }
    }

    @Override
    public String getName() {
        return "Horizontal Shift";
    }
}
