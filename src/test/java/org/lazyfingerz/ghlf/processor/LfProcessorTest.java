package org.lazyfingerz.ghlf.processor;

import org.junit.Test;
import org.lazyfingerz.ghlf.model.Arrangement;
import org.lazyfingerz.ghlf.model.LfImage;
import org.lazyfingerz.ghlf.model.LfSlide;
import org.lazyfingerz.ghlf.processor.LfProcessor;

import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class LfProcessorTest {

    @Test
    public void process() {
/*
        LfImage image_abcde = new LfImage(0, Arrangement.H, Arrays.asList("a", "b", "c", "d", "e"));
        LfImage image_abcfg = new LfImage(0, Arrangement.H, Arrays.asList("a", "b", "c", "f", "g"));
        LfImage image_abcfh = new LfImage(0, Arrangement.H, Arrays.asList("a", "b", "c", "f", "h"));

        LfSlide slide_abcde = new LfSlide(Collections.singletonList(image_abcde));
        LfSlide slide_abcfg = new LfSlide(Collections.singletonList(image_abcfg));
        LfSlide slide_abcfh = new LfSlide(Collections.singletonList(image_abcfh));

        List<LfSlide> a = new LinkedList<>(Arrays.asList(slide_abcde, slide_abcfg, slide_abcfh));
        List<LfSlide> b = new LinkedList<>(Arrays.asList(slide_abcde, slide_abcfg, slide_abcfh));
        List<LfSlide> c = new LinkedList<>(Arrays.asList(slide_abcde, slide_abcfg, slide_abcfh));
        List<LfSlide> d = new LinkedList<>(Arrays.asList(slide_abcde));
        List<LfSlide> e = new LinkedList<>(Arrays.asList(slide_abcde));
        List<LfSlide> f = new LinkedList<>(Arrays.asList(slide_abcfg, slide_abcfh));
        List<LfSlide> g = new LinkedList<>(Arrays.asList(slide_abcfg));
        List<LfSlide> h = new LinkedList<>(Arrays.asList(slide_abcfh));

        Map<String, List<LfSlide>> map = new HashMap<>();

        map.put("a", a);
        map.put("b", b);
        map.put("c", c);
        map.put("d", d);
        map.put("e", e);
        map.put("f", f);
        map.put("g", g);
        map.put("h", h);

        LfProcessor lfProcessor = new LfProcessor();
        List<LfSlide> process = lfProcessor.process(map);

        System.out.println(process);*/
    }
}