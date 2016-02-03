package game_engine;

import java.awt.image.BufferedImage;
import javax.sound.sampled.*;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

//Loads files and assets as necessary

public class Loader {
	public Loader () {
		
	}
	
	public static BufferedImage loadImage (String imagePath) {
		try {
			BufferedImage img = ImageIO.read(new File("./game_engine/assets/" + imagePath));
			return img;
		} catch (IOException e) {
			e.printStackTrace();
			return null;
		}	
	}
	
	public static Clip loadSound(String soundPath) {
		try {
			Clip sound = AudioSystem.getClip();
			AudioInputStream ais = AudioSystem.getAudioInputStream(new File("./game_engine/assets/" + soundPath));
			sound.open(ais);
			
			return sound;
		} catch (LineUnavailableException | UnsupportedAudioFileException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
	}
	
}
