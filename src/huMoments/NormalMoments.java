package huMoments;

import java.awt.Color;
import java.awt.image.BufferedImage;

public class NormalMoments {

	private static double calculateRawMoment(int p, int q, BufferedImage original) {
		double moment = 0.0;

		for (int i = 0; i < original.getWidth(); i++) {
			for (int j = 0; j < original.getHeight(); j++) {
				moment += Math.pow(i, p) * Math.pow(j, q) * (new Color(original.getRGB(i, j))).getBlue();
			}
		}
		return moment;
	}

	public static double getCenterX(BufferedImage original) {
		double x0 = calculateRawMoment(0, 0, original);
		double x1 = calculateRawMoment(1, 0, original);
		return x1 / x0;
	}

	public static double getCenterY(BufferedImage original) {
		double x0 = calculateRawMoment(0, 0, original);
		double x1 = calculateRawMoment(0, 1, original);
		return x1 / x0;
	}

	public static double getCentralMoment(int p, int q, BufferedImage original) {
		double moment = 0;

		int width = original.getWidth();
		int height = original.getHeight();

		double centerX = getCenterX(original);
		double centerY = getCenterY(original);

		for (int j = 0; j < height; j++) {
			for (int i = 0; i < width; i++) {
				moment += Math.pow((i - centerX), p) * Math.pow((j - centerY), q)
						* (new Color(original.getRGB(i, j))).getBlue();
			}
		}
		return moment;
	}

	public static double getNormalCenterMoment(int p, int q, BufferedImage original) {
		double moment = 0;

		double gamma = (p + q) / 2.0 + 1;
		double centralMoment = getCentralMoment(p, q, original);
		double normalfactor = Math.pow(centralMoment, gamma);
		moment = centralMoment / normalfactor;

		return moment;
	}

	private static double meanColor(int chanel, BufferedImage original) {

		int width = original.getWidth();
		int height = original.getHeight();
		double moment = 0;
		for (int j = 0; j < height; j++) {
			for (int i = 0; i < width; i++) {
				switch (chanel) {
				case 0:
					moment += new Color(original.getRGB(i, j)).getRed();
					break;
				case 1:
					moment += new Color(original.getRGB(i, j)).getBlue();
					break;
				case 2:
					moment += new Color(original.getRGB(i, j)).getGreen();
					break;
				}
			}
		}
		moment /= width * height;
		return moment;

	}
	public static double momentColor(int chanel,int h, BufferedImage original) {

		int width = original.getWidth();
		int height = original.getHeight();
		double moment = 0;
		double meanColor = meanColor(chanel, original);
		for (int j = 0; j < height; j++) {
			for (int i = 0; i < width; i++) {
				switch (chanel) {
				case 0:
					moment += Math.pow(new Color(original.getRGB(i, j)).getRed() - meanColor,h);
					break;
				case 1:
					moment += Math.pow(new Color(original.getRGB(i, j)).getBlue() - meanColor,h);
					break;
				case 2:
					moment += Math.pow(new Color(original.getRGB(i, j)).getGreen() - meanColor,h);
					break;
				}
			}
		}
		moment /= width * height;
		moment = Math.pow(moment, 1.0/h);
		return moment;

	}
}
