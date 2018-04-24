package projections;

import java.awt.Color;
import java.awt.image.BufferedImage;

public class Projection {
	public static double[] projectionX(BufferedImage original) {
		double[] proj = new double[original.getWidth()];
		int width = original.getWidth();
		int height = original.getHeight();
		for (int j = 0; j < height; j++) {
			for (int i = 0; i < width; i++) {
				proj[i] += (new Color(original.getRGB(i,j))).getRed()/proj.length;
			}
		}
		return proj;
	}
	public static double[] projectionY(BufferedImage original) {
		double[] proj = new double[original.getHeight()];
		int width = original.getWidth();
		int height = original.getHeight();
		for (int j = 0; j < height; j++) {
			for (int i = 0; i < width; i++) {
				proj[j] += (new Color(original.getRGB(i,j))).getRed()/proj.length;
			}
		}
		return proj;
	}
}
