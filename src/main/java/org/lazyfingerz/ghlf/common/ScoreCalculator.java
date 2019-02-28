package org.lazyfingerz.ghlf.common;

import org.lazyfingerz.ghlf.model.LfImage;
import org.lazyfingerz.ghlf.model.LfSlide;

import java.util.List;

public class ScoreCalculator {

    public int getScore(List<LfSlide> slides){
        int score = 0;
        for(int i=0; i<slides.size()-2; i++){
            score += getScore(slides.get(i), slides.get(i + 1));
        }
        return score;
    }

    public int getScore(LfSlide s1, LfSlide s2){
        List<String> tags1 = s1.getTags();
        List<String> tags2 = s2.getTags();
        int only1 = getOnly(tags1, tags2);
        int only2 = getOnly(tags2, tags1);
        int both = getBoth(tags1, tags2);
        return Math.min(both, Math.min(only1, only2));
    }

    public int getScore(LfImage s1, LfImage s2){
        List<String> tags1 = s1.getTags();
        List<String> tags2 = s2.getTags();
        int only1 = getOnly(tags1, tags2);
        int only2 = getOnly(tags2, tags1);
        int both = getBoth(tags1, tags2);
        return Math.min(both, Math.min(only1, only2));
    }

    private int getOnly(List<String> base, List<String> compared) {
        int count = 0;

        for(String b : base){
            if(!compared.contains(b)){
                count++;
            }
        }
        return count;
    }

    private int getBoth(List<String> base, List<String> compared) {
        int count = 0;
        for(String c : compared){
            if(base.contains(c)){
                count++;
            }
        }
        return count;
    }

}
