package org.lazyfingerz.ghlf.model;

import lombok.Value;

import java.util.List;
import java.util.stream.Collectors;

@Value
public class LfSlide {

    private List<LfImage> images;

    public List<String> getTags(){
        return images.stream()
            .flatMap(i -> i.getTags().stream())
            .distinct()
            .collect(Collectors.toList());
    }

}
