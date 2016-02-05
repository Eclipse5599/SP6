package game_engine;

import java.awt.image.BufferedImage;
import javax.sound.sampled.*;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.imageio.ImageIO;

//Loads files and assets as necessary

public class Loader {
	private Map<String, BufferedImage> loadedImages = new HashMap<String, BufferedImage>();
	private Map<String, Clip> loadedSounds = new HashMap<String, Clip>();
	
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
			System.out.println("This image is already loaded.");
			return loadedImages.get(imagePath);
		} else {
			try {
				BufferedImage img = ImageIO.read(new File(assetsFolder + imagePath));
				
				loadedImages.put(imagePath, img);
				return img;
			} catch (IOException e) {
				e.printStackTrace();
				return null;
			}	
		}
	}
	
	public Clip loadSound(String soundPath) {
		if (loadedSounds.containsKey(soundPath)) {
			System.out.println("This sound is already loaded.");
			return loadedSounds.get(soundPath);
		} else {
			try {
				Clip sound = AudioSystem.getClip();
				AudioInputStream ais = AudioSystem.getAudioInputStream(new File(assetsFolder + soundPath));
				sound.open(ais);
				
				loadedSounds.put(soundPath, sound);
				return sound;
			} catch (LineUnavailableException | UnsupportedAudioFileException | IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				return null;
			}
		}
	}
	
}
