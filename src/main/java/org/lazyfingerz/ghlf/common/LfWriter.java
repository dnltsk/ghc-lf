package org.lazyfingerz.ghlf.common;

import org.lazyfingerz.ghlf.model.LfSlide;

import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.List;

public class LfWriter {

    private ScoreCalculator sc = new ScoreCalculator();

    public void write(List<LfSlide> slides, String filename) throws FileNotFoundException {
        StringBuffer content = new StringBuffer(slides.size() + "\n");
        for (LfSlide s : slides) {
            for (int i = 0; i < s.getImages().size(); i++) {
                content.append(s.getImages().get(i).getIndex());
                if (i < s.getImages().size() - 1) {
                    content.append(" ");
                }
            }
            content.append("\n");
        }

        int score = sc.getScore(slides);
        try (PrintWriter out = new PrintWriter("results/" + score + "-" + filename+"-"+System.currentTimeMillis()+".txt")) {
            out.println(content.toString());
        }
    }
}
