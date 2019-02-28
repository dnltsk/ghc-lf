package org.lazyfingerz.ghlf.common;

import org.junit.Test;
import org.lazyfingerz.ghlf.model.LfImage;
import org.lazyfingerz.ghlf.model.LfSlide;

import java.util.Arrays;

import static org.assertj.core.api.Assertions.assertThat;
import static org.lazyfingerz.ghlf.model.Arrangement.H;

public class ScoreCalculatorTest {

    ScoreCalculator sc = new ScoreCalculator();

    @Test
    public void test(){
        LfSlide s1 = new LfSlide(Arrays.asList(new LfImage(0, H, Arrays.asList("a", "c", "1", "2"))));
        LfSlide s2 = new LfSlide(Arrays.asList(new LfImage(0, H, Arrays.asList("b", "c", "d", "e"))));
        // only "c" id in both!
        int score = sc.getScore(s1, s2);
        assertThat(score).isEqualTo(1);
    }

}