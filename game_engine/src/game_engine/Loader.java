package game_engine;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

//Loads files and assets as necessary

public class Loader {
	public Loader () {
		
	}
	
	public static BufferedImage loadImage (String imagePath) {
		try {
			BufferedImage img = ImageIO.read(new File("./assets/" + imagePath));
			return img;
		} catch (IOException e) {
			e.printStackTrace();
			return null;
		}
		
	}
}
