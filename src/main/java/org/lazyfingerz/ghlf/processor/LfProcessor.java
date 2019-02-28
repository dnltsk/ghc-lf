package org.lazyfingerz.ghlf.processor;

import org.lazyfingerz.ghlf.common.ScoreCalculator;
import org.lazyfingerz.ghlf.model.LfImage;
import org.lazyfingerz.ghlf.model.LfSlide;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class LfProcessor {

    public List<LfSlide> process(Map<String, List<LfSlide>> map, List<LfSlide> inputSlides) {

        ScoreCalculator scoreCalculator = new ScoreCalculator();

        List<LfSlide> slideshow = new ArrayList<>(4096);

        LfSlide current = getFirstSlide(inputSlides);

        //TODO: size should be total amount of remaining slides
        while (inputSlides.size() > 0) {

            if (current == null) current = getFirstSlide(inputSlides);

            inputSlides.remove(current);
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
                    if (score >= current.getTags().size()) break;
                }
                if (bestScore >= current.getTags().size()) break;
            }
            current = bestNext;
        }

        return slideshow;
    }

    public List<LfSlide> deleteAllSlidesWithImage(List<LfSlide> slides, LfImage image) {
        return slides.stream().filter(slide -> !slide.getImages().contains(image)).collect(Collectors.toList());
    }

    private LfSlide getFirstSlide(List<LfSlide> slides) {
        return slides.get(0);
    }
}
