package org.lazyfingerz.ghlf;
import org.lazyfingerz.ghlf.model.LfImage;
import org.lazyfingerz.ghlf.model.LfSlide;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class DemoSlider {

    public List<LfSlide> createSlides(List<LfImage> images){
         return images.stream().map(image -> {
            return new LfSlide(Collections.singletonList(image));
        }).collect(Collectors.toList());
    }

}
