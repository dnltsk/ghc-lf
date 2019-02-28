package org.lazyfingerz.ghlf;

import org.lazyfingerz.ghlf.model.Arrangement;
import org.lazyfingerz.ghlf.model.LfImage;
import org.lazyfingerz.ghlf.model.LfSlide;

import java.util.Arrays;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

public class DemoSlider {

    public List<LfSlide> createSlides(List<LfImage> images) {
        Collections.shuffle(images);

        List<LfImage> verts = images.stream().filter(i -> i.getArrangement() == Arrangement.V).collect(Collectors.toList());
        List<LfImage> horts = images.stream().filter(i -> i.getArrangement() == Arrangement.H).collect(Collectors.toList());

        List<List<LfImage>> vertGroups = new LinkedList<>();
        for (int i = 0; i < (verts.size() / 2.0); i++) {
            vertGroups.add(Arrays.asList(verts.get(i), verts.get(i + 1)));
        }

        List<LfSlide> hSlides = horts.stream().map(image -> {
            return new LfSlide(Collections.singletonList(image));
        }).collect(Collectors.toList());


        List<LfSlide> vSlides = vertGroups.stream().map(vg -> {
            return new LfSlide(Arrays.asList(vg.get(0), vg.get(0)));
        }).collect(Collectors.toList());

        hSlides.addAll(vSlides);

        Collections.shuffle(hSlides);

        return hSlides;

    }

}
