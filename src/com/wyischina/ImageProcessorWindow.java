package com.wyischina;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class ImageProcessorWindow extends JFrame {

    /**
     * Processors.
     */
    private List<ImageProcessor> processors;

    /**
     * The label for displaying the image.
     */
    private JLabel label;

    /**
     * Parameter value.
     */
    private Map<ImageProcessor, Float> parameters;

    /**
     * Parameter value.
     */
    private Map<ImageProcessor, Boolean> statuses;


    /**
     * Input image.
     */
    private BufferedImage inputImage;


    /**
     * Create a Window that will display the processed image.
     *
     * @param imageUrl   the URL to load the image.
     * @param processors list of processors user may choose to process the image.
     */
    public ImageProcessorWindow(String imageUrl, List<ImageProcessor> processors) {
        this.processors = processors;
        this.parameters = new HashMap<>();
        this.statuses = new HashMap<>();

        for (ImageProcessor processor : processors) {
            this.parameters.put(processor, 0f);
            this.statuses.put(processor, true);
        }

        // load the input image
        try {
            InputStream is = new URL(imageUrl).openStream();
            inputImage = ImageIO.read(is);
        } catch (IOException e) {
            throw new IllegalArgumentException("unable to read image - " + e.getMessage());
        }


        // set up the window's size and shape
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        Toolkit tk = Toolkit.getDefaultToolkit();
        int xSize = ((int) tk.getScreenSize().getWidth());
        int ySize = ((int) tk.getScreenSize().getHeight());
        setSize(xSize, ySize);
        setTitle("WYIS Image Processor Exercise");

        // add a toolbar at the top of the window
        JPanel toolbar = new JPanel();
        BoxLayout layout = new BoxLayout(toolbar, BoxLayout.LINE_AXIS);
        toolbar.setLayout(layout);
        add(toolbar, BorderLayout.NORTH);

        for (ImageProcessor processor : processors) {
            // add a label for each processor
            final ImageProcessor thisProcessor = processor;

            JCheckBox checkbox = new JCheckBox(new AbstractAction(thisProcessor.getName()) {
                @Override
                public void actionPerformed(ActionEvent e) {
                    JCheckBox checkbox = (JCheckBox) e.getSource();
                    ImageProcessorWindow.this.statuses.put(thisProcessor, checkbox.isSelected());
                    scheduleRepaint();
                }
            });
            checkbox.setSelected(this.statuses.get(thisProcessor));
            toolbar.add(checkbox);


            // add a slider for the user parameter for that processor
            JSlider slider = new JSlider(-100, 100, 0);
            slider.setMajorTickSpacing(5);
            slider.setMinorTickSpacing(1);
            slider.setSize(new Dimension(150, 30));
            slider.addChangeListener(e -> {
                ImageProcessorWindow.this.parameters.put(thisProcessor, slider.getValue() / 100f);
                scheduleRepaint();
            });
            toolbar.add(slider);
        }

        // add the label to a scroll pane in the middle of the screen
        this.label = new JLabel(new ImageIcon(this.inputImage));
        JScrollPane scrollPane = new JScrollPane(this.label);
        scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        add(scrollPane, BorderLayout.CENTER);
        pack();
        scheduleRepaint();
    }

    /**
     * Schedule a repaint of the output image by invoking the currently selected processor.
     */
    private void scheduleRepaint() {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                ImageProcessorWindow.this.redrawProcessedImage();
            }
        });
    }

    /**
     * Redraw the processed image.
     */
    private void redrawProcessedImage() {
        // get the current processor and process the image.
        BufferedImage input = this.inputImage;
        BufferedImage output = new BufferedImage(inputImage.getWidth(), inputImage.getHeight(), inputImage.getType());
        for (ImageProcessor currentProcessor : processors.stream().filter(p -> this.statuses.get(p)).collect(Collectors.toList())) {
            currentProcessor.process(new BufferedImageDelegate(input),
                    new BufferedImageDelegate(output),
                    parameters.get(currentProcessor));
            input = output;
            output = new BufferedImage(inputImage.getWidth(), inputImage.getHeight(), inputImage.getType());
        }

        // update the label's image
        this.label.setIcon(new ImageIcon(input));
    }
}
