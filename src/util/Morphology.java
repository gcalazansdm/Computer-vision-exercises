package util;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import Threshold.Threshoold;

public final class Morphology {
	public static BufferedImage erode(BufferedImage original, int windowSize) {
		int cor = 0;
		int width = original.getWidth();
		int height = original.getHeight();
		BufferedImage tempImage = new BufferedImage(width, height, BufferedImage.TYPE_BYTE_BINARY);
		for (int j = 0; j < height; j++) {
			for (int i = 0; i < width; i++) {
				cor = Color.WHITE.getRGB();
				for (int k = -windowSize; k < windowSize; k++) {
					for (int l = -windowSize; l < windowSize; l++) {
						if (i + k > 0 && i + k < width && j + l > 0 && j + l < height) {
							if (original.getRGB(i + k, j + l) == Color.BLACK.getRGB()) {
								cor = Color.BLACK.getRGB();
								k = windowSize;
								break;
							}
						}
					}
				}
				tempImage.setRGB(i, j, cor);
			}
		}

		return tempImage;
	}

	public static BufferedImage subtract(BufferedImage original, BufferedImage img2) {
		int width = original.getWidth();
		int height = original.getHeight();
		BufferedImage tempImage = new BufferedImage(width, height, BufferedImage.TYPE_BYTE_BINARY);
		for (int j = 0; j < height; j++) {
			for (int i = 0; i < width; i++) {
				if (original.getRGB(i, j) == Color.WHITE.getRGB() && img2.getRGB(i, j) == Color.BLACK.getRGB()) {
					tempImage.setRGB(i, j, Color.WHITE.getRGB());
				}
				else{
					tempImage.setRGB(i, j, Color.BLACK.getRGB());
				}
			}
		}

		return tempImage;
	}
	
	public static int getArea(BufferedImage original) {
		int width = original.getWidth();
		int height = original.getHeight();
		int rValue = 0;
		for (int j = 0; j < height; j++) {
			for (int i = 0; i < width; i++) {
				if (original.getRGB(i, j) == Color.WHITE.getRGB()) {
					++rValue;
				}
			}
		}

		return rValue;
	}
	
	public static int getPerimeter(BufferedImage original) {
	
		return getArea(subtract(original,erode(original, 1)));
	}
	
	public static void main(String[] args) {

		try {
			String imagePath = "C:/Users/Gabriel/Desktop/OrganiZen/2.png";
			String resultPath = "C:/Users/Gabriel/Desktop/OrganiZen/1.png";

			File imageFile = new File(imagePath);
			File resultFile = new File(resultPath);
			if (!resultFile.exists())
				resultFile.createNewFile();

			BufferedImage image;
			image = ImageIO.read(imageFile);

			ImageIO.write(subtract(image,erode(image, 2)), "png", resultFile);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
