package org.lazyfingerz.ghlf;

import org.lazyfingerz.ghlf.common.LfReader;
import org.lazyfingerz.ghlf.common.LfWriter;
import org.lazyfingerz.ghlf.model.LfImage;
import org.lazyfingerz.ghlf.model.LfSlide;

import java.io.IOException;
import java.util.List;

public class Main {

	public static void main(String[] args) throws IOException {
		List<LfImage> images = new LfReader().read("a_example.txt");
		List<LfSlide> slides = new DemoSlider().createSlides(images);
		new LfWriter().write(slides);
	}

}
