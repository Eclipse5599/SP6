package game_engine;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URL;
import java.util.concurrent.ConcurrentHashMap;

import javax.imageio.ImageIO;

//Loads files and assets as necessary

public class Loader {
	private ConcurrentHashMap<String, BufferedImage> loadedImages = new ConcurrentHashMap<String, BufferedImage>();
	private ConcurrentHashMap<String, URL> loadedSounds = new ConcurrentHashMap<String, URL>();
	
	private String assetsFolder = "./assets/";
	
	private Loader () {
		
	}
	
	public static Loader getInstance () {
		if (Constants.theLoader == null) {
			Constants.theLoader = new Loader();
		}
		return Constants.theLoader;
	}
	
	
	public BufferedImage loadImage (String imagePath) {
		if (loadedImages.containsKey(imagePath)) {
			return loadedImages.get(imagePath);
		} else {
			try {
				BufferedImage img = null;
				img = ImageIO.read(new URL(new URL("file:"),assetsFolder + imagePath));
				
				loadedImages.put(imagePath, img);
				return img;
			} catch (IOException e) {
				e.printStackTrace();
				return null;
			}	
		}
	}
	
	public URL loadSound(String soundPath) {
		if (loadedSounds.containsKey(soundPath) && loadedSounds.get(soundPath) != null) {
			return loadedSounds.get(soundPath);
		} else {
			try {
				URL theSoundFile = new URL(new URL("file:"), assetsFolder + soundPath);
				loadedSounds.put(soundPath, theSoundFile);
				return theSoundFile;
			} catch (Exception e) {
				e.printStackTrace();
				return null;
			}
		}
	}
	
}
