package formRepresentation;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Arrays;

import javax.imageio.ImageIO;

import Threshold.Threshoold;
import huMoments.ColorMoment;
import huMoments.NormalMoments;
import util.Morphology;

public class MassCenter {
	//(X,Y)
	public static double[] MassCenter(final BufferedImage original) {
		double m10 = NormalMoments.getCentralMoment(1,0,original);
		double m01 = NormalMoments.getCentralMoment(0,1,original);
		double m00 = NormalMoments.getCentralMoment(0,0,original);
		System.out.println(m10);
		System.out.println(m01);
		System.out.println(m00);
		double[] rValue = new double[2];
		
		rValue[0] = Math.round(m10/m00);
		rValue[1] = Math.round(m01/m00);
		
		return rValue;
	}

	public static void main(String[] args) {

		try {
			String imagePath = "C:/Users/Gabriel/Desktop/OrganiZen/Nova pasta (3)/01_01.jpg";

			File imageFile = new File(imagePath);

			BufferedImage image;
			image = ImageIO.read(imageFile);

			System.out.println(Arrays.toString(MassCenter(Threshoold.Sauvola(image, 2))));
		} catch (IOException e) {
			e.printStackTrace();
		}

	}
}
