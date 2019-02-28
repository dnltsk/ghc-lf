package org.lazyfingerz.ghlf.sort_by_list;

import org.lazyfingerz.ghlf.model.LfImage;
import org.lazyfingerz.ghlf.model.LfSlide;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import static org.lazyfingerz.ghlf.model.Arrangement.H;
import static org.lazyfingerz.ghlf.model.Arrangement.V;

public class SimpleSlider {

    public List<LfSlide> createAllPossibleSlides(List<LfImage> images) {

        // horizontal: 1 image -> 1 slide
        List<LfSlide> slides = images.stream().filter(image -> image.getArrangement().equals(H))
            .map(image -> {
                return new LfSlide(Collections.singletonList(image));
            }).collect(Collectors.toList());

        images = images.stream().filter(i -> i.getArrangement().equals(V)).collect(Collectors.toList());

        // vertical: 2 images -> 1 slide. add all possibilities
        for (int i = 0; i < images.size(); i++) {
            for (int j = i + 1; j < images.size(); j++) {
                slides.add(new LfSlide(Arrays.asList(images.get(i), images.get(j))));
            }
        }

        return slides;
    }

    public List<LfSlide> deleteAllSlidesWithImage(List<LfSlide> slides, LfImage image) {
        return slides.stream().filter(slide -> !slide.getImages().contains(image)).collect(Collectors.toList());
    }


}
