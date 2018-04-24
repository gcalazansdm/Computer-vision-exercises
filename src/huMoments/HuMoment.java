package huMoments;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Arrays;

import javax.imageio.ImageIO;

import Threshold.Threshoold;

public class HuMoment {
	public static double[] calculate(BufferedImage original)
	{
		double[]  moments = new double[7];
	
		double nm11 = NormalMoments.getNormalCenterMoment(1, 1, original);
		double nm12 = NormalMoments.getNormalCenterMoment(1, 2, original);
		double nm21 = NormalMoments.getNormalCenterMoment(2, 1, original);
		double nm20 = NormalMoments.getNormalCenterMoment(2, 0, original);
		double nm02 = NormalMoments.getNormalCenterMoment(0, 2, original);
		double nm30 = NormalMoments.getNormalCenterMoment(3, 0, original);
		double nm03 = NormalMoments.getNormalCenterMoment(0, 3, original);
	    
	
	    moments[0] = nm20 + nm02;
	
	    moments[1] = Math.pow((nm20 - nm02),2.0) + 4 * Math.pow(nm11,2.0);
	    
	    moments[2] = Math.pow((nm30 - 3 * nm12),2.0) + 4 * Math.pow(3 * nm21 - nm03,2.0);
	  
	    moments[3] = Math.pow((nm30 + nm12),2.0) + 4 * Math.pow(nm21 - nm03,2.0);
	
	    moments[4] = (nm30 - 3 * nm12)*(nm30 + nm12)*(Math.pow((nm30 + nm12), 2.0)) - 3*(Math.pow((nm21 + nm03), 2.0)) 
	    		+ 3 * (nm21 - nm03) * (nm21 + nm03) * 3 *(nm30 + nm12)*(nm30 + nm12) - (nm21 + nm03)*(nm21 + nm03);
	
	    moments[5] = (nm20 - nm02)*(nm30 + nm12)*(nm30 + nm12) - (nm21 + nm03)*(nm21 + nm03) + 4*nm11*(nm30 + nm12)*(nm21 + nm03);
	    
	    moments[6] = ( 3 * nm21 - nm03)*(nm30 + nm12)*((nm30 + nm12)*(nm30 + nm12)- 3*(nm21 + nm03)*(nm21 + nm03))
	    		+ (3 * nm12 - nm30) * (nm21 + nm03) * (3*(nm30 + nm12)*(nm30 + nm12) - ((nm21 + nm03)*(nm21 + nm03)));
	
	    return moments;
	}
	public static void main(String[] args) {

			try {
				String imagePath = "C:/Users/Gabriel/Desktop/OrganiZen/Nova pasta (3)/01_01.jpg";
				String resultPath = "C:/Users/Gabriel/Desktop/OrganiZen/Nova pasta (3)/2.png";

				File imageFile = new File(imagePath);
				File resultFile = new File(resultPath);
				if (!resultFile.exists())
					resultFile.createNewFile();

				BufferedImage image;
				image = ImageIO.read(imageFile);
				System.out.println(1);
				BufferedImage grayImage = Threshoold.toGray(image);
				System.out.println(Arrays.toString(HuMoment.calculate(grayImage)));
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		
	}
}
