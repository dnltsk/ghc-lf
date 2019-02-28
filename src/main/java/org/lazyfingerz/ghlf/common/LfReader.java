package org.lazyfingerz.ghlf.common;

import org.lazyfingerz.ghlf.model.Arrangement;
import org.lazyfingerz.ghlf.model.LfImage;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

public class LfReader {

    public List<LfImage> read(String filename) throws IOException {
        List<LfImage> images = new LinkedList<>();

        try (BufferedReader br = new BufferedReader(new FileReader(filename))) {
            int lineCounter = 0;
            for (String line; (line = br.readLine()) != null; ) {
                if (lineCounter > 0) {
                    images.add(parseImage(line, lineCounter-1));
                }
                lineCounter++;
            }
        }
        return images;
    }

    private LfImage parseImage(String line, int imageIndex) {
        List<String> elements = new LinkedList(Arrays.asList((line.split(" "))));
        Arrangement arrangement = Arrangement.valueOf(elements.get(0));
        int numberOfTags = Integer.parseInt(elements.get(1));
        elements.remove(0);
        elements.remove(0);
        LfImage image = new LfImage(
            imageIndex,
            arrangement,
            numberOfTags,
            elements
        );
        return image;
    }

}
