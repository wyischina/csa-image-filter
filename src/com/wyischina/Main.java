package com.wyischina;

import javax.swing.*;
import java.util.ArrayList;
import java.util.List;

public class Main {

    public static void main(String[] args) {
        // the image URL
        String pictureUrl ="https://placekitten.com/408/287";

        // create a list of processors
        List<ImageProcessor> processors = new ArrayList<ImageProcessor>();
        processors.add(new BrightnessImageProcessor());
        processors.add(new HorizontalShiftImageProcessor());
        processors.add(new HorizontalScaleImageProcessor());
        processors.add(new BoxBlurImageProcessor());


        // create the image processor window and display it.
        JFrame window = new ImageProcessorWindow(pictureUrl, processors);
        window.setVisible(true);
    }
}
