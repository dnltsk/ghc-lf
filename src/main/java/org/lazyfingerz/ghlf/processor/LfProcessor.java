package org.lazyfingerz.ghlf.processor;

import org.lazyfingerz.ghlf.common.ScoreCalculator;
import org.lazyfingerz.ghlf.model.LfImage;
import org.lazyfingerz.ghlf.model.LfSlide;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class LfProcessor {

    public List<LfSlide> process(Map<String, List<LfSlide>> map) {

        ScoreCalculator scoreCalculator = new ScoreCalculator();

        List<LfSlide> slideshow = new ArrayList<>(4096);

        LfSlide current = getFirstSlide(map);

        //TODO: size should be total amount of remaining slides
        while (current != null) {

            slideshow.add(current);

            Integer bestScore = 0;
            LfSlide bestNext = null;

            for (String tag : current.getTags()) {

                List<LfSlide> slidesWithSameTag = map.get(tag);

                for (LfImage image : current.getImages()) {
                    slidesWithSameTag = deleteAllSlidesWithImage(slidesWithSameTag, image);
                    map.remove(tag);
                    map.put(tag, slidesWithSameTag);
                }

                for (LfSlide candidateNext : slidesWithSameTag) {
                    Integer score = scoreCalculator.getScore(current, candidateNext);
                    if (score >= bestScore) {
                        bestNext = candidateNext;
                        bestScore = score;
                    }
                }
            }
            current = bestNext;
        }

        return slideshow;
    }

    public List<LfSlide> deleteAllSlidesWithImage(List<LfSlide> slides, LfImage image) {
        return slides.stream().filter(slide -> !slide.getImages().contains(image)).collect(Collectors.toList());
    }

    private LfSlide getFirstSlide(Map<String, List<LfSlide>> map) {
        List<LfSlide> firstList = (List<LfSlide>) map.values().toArray()[0];
        return firstList.get(0);
    }
}
