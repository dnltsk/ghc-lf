package org.lazyfingerz.ghlf;

import org.lazyfingerz.ghlf.common.LfReader;
import org.lazyfingerz.ghlf.common.LfWriter;
import org.lazyfingerz.ghlf.model.LfImage;
import org.lazyfingerz.ghlf.model.LfSlide;

import java.io.IOException;
import java.util.List;

public class Main {

    private final static String A = "a_example.txt";
    private final static String B = "b_lovely_landscapes.txt";
    private final static String C = "c_memorable_moments.txt";
    private final static String D = "d_pet_pictures.txt";
    private final static String E = "e_shiny_selfies.txt";

    public static void main(String[] args) throws IOException {

        String filename = A;

        List<LfImage> images = new LfReader().read(filename);
        List<LfSlide> slides = new DemoSlider().createSlides(images);
        new LfWriter().write(slides, filename);
    }

}
