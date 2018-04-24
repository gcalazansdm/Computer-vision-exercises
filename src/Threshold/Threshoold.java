package Threshold;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class Threshoold {

	public static double k = -0.5;
	public static int r = 255;
	public static int c = 0;

	public static BufferedImage toGray(BufferedImage original) {

		int width = original.getWidth();
		int height = original.getHeight();
		int value;
		Color color;
		BufferedImage tempImage = new BufferedImage(width, height, BufferedImage.TYPE_BYTE_GRAY);
		for (int j = 0; j < height; ++j) {
			for (int i = 0; i < width; ++i) 
			{
				color = new Color(original.getRGB(i, j));
				value = (int) Math.round(0.2126* color.getRed() + 0.7152*color.getGreen() + 0.0722*color.getBlue());
				tempImage.setRGB(i, j, new Color(value,value,value).getRGB());
			}
			}
		return tempImage;
	}
	public static BufferedImage Sauvola(BufferedImage original, int windowRadius) {
		int width = original.getWidth();
		int height = original.getHeight();
		int[] values;
		double mean;
		double stddev;
		double threshold = 0;
		int count;
		BufferedImage tempImage = new BufferedImage(width, height, BufferedImage.TYPE_BYTE_BINARY);
		for (int j = 0; j < height; ++j) {
			for (int i = 0; i < width; ++i) {
				values = new int[(windowRadius * 2 + 1)*(windowRadius * 2 + 1)];
				count=0;
				for (int k = -windowRadius; k < windowRadius; k++) {
					for (int l = -windowRadius; l < windowRadius; l++) {
						if (i + k > 0 && i + k < width && j + l > 0 && j + l < height) {
							values[count] = new Color(original.getRGB(i + k, j + l)).getRed();
						}
						++count;
					}
				}
				mean = mean(values);
				stddev = stddev(values, mean);
				threshold = mean * (1 + Threshoold.k * (stddev / r - 1));
				if (new Color(original.getRGB(i, j)).getRed() > threshold) {
					tempImage.setRGB(i, j, Color.WHITE.getRGB());
				} else {
					tempImage.setRGB(i, j, Color.BLACK.getRGB());
				}
			}
		}
		return tempImage;

	}
	
	public static BufferedImage Niblack(BufferedImage original, int windowRadius) {
		int width = original.getWidth();
		int height = original.getHeight();
		int[] values;
		double mean;
		double stddev;
		double threshold = 0;
		int count = 0;
		BufferedImage tempImage = new BufferedImage(width, height, BufferedImage.TYPE_BYTE_BINARY);
		for (int j = 0; j < height; ++j) {
			for (int i = 0; i < width; ++i) {
				values = new int[(windowRadius * 2 + 1)*(windowRadius * 2 + 1)];
				for (int k = -windowRadius; k < windowRadius; k++) {
					for (int l = -windowRadius; l < windowRadius; l++) {
						if (i + k > 0 && i + k < width && j + l > 0 && j + l < height) {
							values[count] = new Color(original.getRGB(i + k, j + l)).getRed();
						}
						++count;
					}
				}
				mean = mean(values);
				stddev = stddev(values, mean);
				threshold = mean + Threshoold.k * stddev - c;
				if (new Color(original.getRGB(i, j)).getRed() > threshold) {
					tempImage.setRGB(i, j, Color.WHITE.getRGB());
				} else {
					tempImage.setRGB(i, j, Color.BLACK.getRGB());
				}
			}
		}
		return tempImage;

	}

	private static double mean(int[] values) {
		double rValue = 0.0;
		for (int i = 0; i < values.length; ++i) {
			rValue += values[i] * 1.0;
		}
		rValue /= values.length;
		return rValue;
	}

	private static double stddev(int[] values, double mean) {
		double rValue = 0.0;
		for (int i = 0; i < values.length; ++i) {
			rValue += Math.pow((values[i] - mean), 2);
		}
		rValue /= values.length;
		rValue = Math.sqrt(rValue);
		return rValue;
	}

	public static void main(String[] args) {

		try {
			String imagePath = "C:/Users/Gabriel/Desktop/OrganiZen/Nova pasta (3)/01_03.jpg";
			String resultPath = "C:/Users/Gabriel/Desktop/OrganiZen/2.png";

			File imageFile = new File(imagePath);
			File resultFile = new File(resultPath);
			if (!resultFile.exists())
				resultFile.createNewFile();

			BufferedImage image;
			image = ImageIO.read(imageFile);

			ImageIO.write(Sauvola(toGray(image), 2), "png", resultFile);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
