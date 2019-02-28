package org.lazyfingerz.ghlf.sort_by_tags;

import org.lazyfingerz.ghlf.model.LfSlide;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SortByTags {

    // List of Slides goes in
    public Map<String, List<LfSlide>> SortByTags(List<LfSlide> slides) {
        Map<String, List<LfSlide>> map = new HashMap<>();

        // iterate over all slides
        slides.forEach(slide -> slide.getTags() // iterate over all tags of a slide
            .forEach(tag -> {
                if (!map.containsKey(tag)) {
                    // add new entry with (tag, new ArrayList(slide1))
                    map.put(tag, addFirstSlide(slide));
                } else {
                    // add slide to existing entry
                    map.put(tag, addNextSlide(map.get(tag), slide));
                }
        }));

        return map;
    }

    private List<LfSlide> addFirstSlide(LfSlide slide) {
        List<LfSlide> listOfSlides =  new ArrayList();
        listOfSlides.add(slide);
        return listOfSlides;
    }

    private List<LfSlide> addNextSlide(List<LfSlide> previousSlides, LfSlide nextSlide) {
        previousSlides.add(nextSlide);
        return previousSlides;
    }

}
