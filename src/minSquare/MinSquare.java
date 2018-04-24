package minSquare;

import java.awt.image.BufferedImage;

import util.Morphology;

public class MinSquare {

	public static double MinSquare(final BufferedImage original) {
		double rFactor = 4 * Math.PI * Morphology.getArea(original);
		return rFactor;
	}
}
