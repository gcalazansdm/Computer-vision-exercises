package huMoments;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Arrays;

import javax.imageio.ImageIO;


public class ColorMoment {
	public static double[] calculate(BufferedImage original, int h) {
		double[] moments = new double[3];

		moments[0] = NormalMoments.momentColor(0, h, original);
		moments[1] = NormalMoments.momentColor(1, h, original);
		moments[2] = NormalMoments.momentColor(2, h, original);

		return moments;
	}

	public static void main(String[] args) {

		try {
			String imagePath = "C:/Users/Gabriel/Desktop/OrganiZen/Nova pasta (3)/01_01.jpg";

			File imageFile = new File(imagePath);

			BufferedImage image;
			image = ImageIO.read(imageFile);

			System.out.println(Arrays.toString(ColorMoment.calculate(image,2)));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
}
