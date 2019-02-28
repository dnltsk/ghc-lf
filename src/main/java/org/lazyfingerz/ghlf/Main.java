package org.lazyfingerz.ghlf;

import org.lazyfingerz.ghlf.common.LfReader;
import org.lazyfingerz.ghlf.common.LfWriter;
import org.lazyfingerz.ghlf.model.LfImage;
import org.lazyfingerz.ghlf.model.LfSlide;
import org.lazyfingerz.ghlf.processor.LfProcessor;
import org.lazyfingerz.ghlf.sort_by_list.SimpleSlider;
import org.lazyfingerz.ghlf.sort_by_tags.SortByTags;

import java.io.IOException;
import java.util.List;
import java.util.Map;

public class Main {

    private final static String A = "a_example.txt";
    private final static String B = "b_lovely_landscapes.txt";
    private final static String C = "c_memorable_moments.txt";
    private final static String D = "d_pet_pictures.txt";
    private final static String E = "e_shiny_selfies.txt";
    private final static String F = "f_small_example.txt";

    public static void main(String[] args) throws IOException {

        String filename = F;

        List<LfImage> images = new LfReader().read(filename);
        List<LfSlide> slides = new SimpleSlider().createAllPossibleSlides(images);
        Map<String, List<LfSlide>> tagMap = new SortByTags().sortByTags(slides);
        slides = new LfProcessor().process(tagMap);
        new LfWriter().write(slides, filename);
    }

}
