package org.lazyfingerz.ghlf.sort_by_tags;

import org.lazyfingerz.ghlf.model.LfSlide;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SortByTags {

    public Map<String, List<LfSlide>> SortByTags(List<LfSlide> slides) {
        Map<String, List<LfSlide>> map = new HashMap<>();

        slides.forEach(slide -> slide.getTags()
            .forEach(tag -> {
                if (!map.containsKey(tag)) {
                    map.put(tag, addFirstSlide(slide));
                } else {
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
