package org.lazyfingerz.ghlf.sort_by_tags;


import org.junit.Test;
import org.lazyfingerz.ghlf.model.LfImage;
import org.lazyfingerz.ghlf.model.LfSlide;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;
import static org.lazyfingerz.ghlf.model.Arrangement.H;

public class SortByTagsTest {

    private SortByTags sortByTags = new SortByTags();

    @Test
    public void test() {
        LfSlide s1 = new LfSlide(Arrays.asList(new LfImage(0, H, Arrays.asList("a", "b", "c", "d"))));
        LfSlide s2 = new LfSlide(Arrays.asList(new LfImage(0, H, Arrays.asList("b", "c", "d", "1"))));
        LfSlide s3 = new LfSlide(Arrays.asList(new LfImage(0, H, Arrays.asList("c", "d", "1", "2"))));
        LfSlide s4 = new LfSlide(Arrays.asList(new LfImage(0, H, Arrays.asList("d", "1", "2", "3"))));

        Map<String, List<LfSlide>> map = sortByTags.sortByTags(Arrays.asList(s1, s2, s3, s4));

        assertThat(map.get("a").size()).isEqualTo(1);
        assertThat(map.get("b").size()).isEqualTo(2);
        assertThat(map.get("c").size()).isEqualTo(3);
        assertThat(map.get("d").size()).isEqualTo(4);
        assertThat(map.get("1").size()).isEqualTo(3);
        assertThat(map.get("2").size()).isEqualTo(2);
        assertThat(map.get("3").size()).isEqualTo(1);
    }
}