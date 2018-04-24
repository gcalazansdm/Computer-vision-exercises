package formRepresentation;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import util.Morphology;

public class FormFactor {
	public static double FormFactor(final BufferedImage original) {
		double rFactor = 4 * Math.PI * Morphology.getArea(original);
		rFactor /= (Morphology.getPerimeter(original) * Morphology.getPerimeter(original));
		return rFactor;
	}
	
	public static void main(String[] args) {

		try {
			String imagePath = "C:/Users/Gabriel/Desktop/OrganiZen/Nova pasta (3)/01_03.jpg";

			File imageFile = new File(imagePath);
			
			BufferedImage image;
			image = ImageIO.read(imageFile);
			System.out.println(FormFactor(image));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
