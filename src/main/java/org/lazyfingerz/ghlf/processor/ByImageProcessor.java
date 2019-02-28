package org.lazyfingerz.ghlf.processor;

import org.lazyfingerz.ghlf.common.ScoreCalculator;
import org.lazyfingerz.ghlf.model.Arrangement;
import org.lazyfingerz.ghlf.model.LfImage;
import org.lazyfingerz.ghlf.model.LfSlide;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class ByImageProcessor {

    public List<LfSlide> process(Map<String, List<LfImage>> map, List<LfImage> images) {

        List<LfImage> verticalImages = images.stream().filter(i -> i.getArrangement().equals(Arrangement.V)).collect(Collectors.toList());

        List<LfImage> usedImages = new ArrayList<>();

        ScoreCalculator scoreCalculator = new ScoreCalculator();

        List<LfSlide> slideshow = new ArrayList<>(4096);

        LfImage current;

        while (!images.isEmpty()) {

            current = images.get(0);

            //TODO: size should be total amount of remaining slides
            while (current != null) {

                LfImage toRemove;

                if (current.getArrangement() == Arrangement.H) {
                    slideshow.add(new LfSlide(Collections.singletonList(current)));
                    images.remove(current);
                    toRemove = null;
                    usedImages.add(current);
                } else {
                    // vertical image -> another one is needed!
                    Integer bestScore = 0;
                    LfImage bestNext;
                    if (verticalImages.size() > 0)
                        bestNext = verticalImages.get(0);
                    else
                        break;
                    for (LfImage candidateNext : verticalImages) {
                        Integer score = scoreCalculator.getScore(current, candidateNext);
                        if (score >= bestScore) {
                            bestNext = candidateNext;
                            bestScore = score;
                        }
                    }
                    if (!usedImages.contains(current) && !usedImages.contains(bestNext))
                        slideshow.add(new LfSlide(Arrays.asList(current, bestNext)));
                    usedImages.add(current);
                    usedImages.add(bestNext);
                    images.remove(current);
                    images.remove(bestNext);
                    verticalImages.remove(current);
                    verticalImages.remove(bestNext);
                    toRemove = bestNext;
                }

                Integer bestScore = 0;
                LfImage bestNext = null;

                List<LfImage> imagesWithSameTag = new ArrayList<>();

                for (String tag : current.getTags()) {

                    imagesWithSameTag = map.get(tag);
                    imagesWithSameTag.remove(current);
                    if (toRemove != null && imagesWithSameTag.contains(toRemove))
                        imagesWithSameTag.remove(toRemove);
                    map.remove(tag);
                    map.put(tag, imagesWithSameTag);


                    for (LfImage candidateNext : imagesWithSameTag) {
                        Integer score = scoreCalculator.getScore(current, candidateNext);
                        if (score >= bestScore) {
                            bestNext = candidateNext;
                            bestScore = score;
                        }
                    }
                }
                current = bestNext;
            }

            LfSlide lastSlide = slideshow.get(slideshow.size() - 1);
            if (lastSlide.getImages().contains(null)) {
                slideshow.remove(lastSlide);
            }
        }

        return slideshow;
    }

    public List<LfSlide> deleteAllSlidesWithImage(List<LfSlide> slides, LfImage image) {
        return slides.stream().filter(slide -> !slide.getImages().contains(image)).collect(Collectors.toList());
    }

    private LfImage getFirstImage(Map<String, List<LfImage>> map) {
        List<LfImage> firstList = (List<LfImage>) map.values().toArray()[0];
        return firstList.get(0);
    }
}
